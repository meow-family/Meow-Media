package com.octopus.socialnetwork.data.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.octopus.socialnetwork.data.local.database.SocialDatabase
import com.octopus.socialnetwork.data.local.entity.PostEntity
import com.octopus.socialnetwork.data.local.entity.RemoteKeyEntity
import com.octopus.socialnetwork.data.mapper.toPostEntity
import com.octopus.socialnetwork.data.remote.service.SocialService
import com.octopus.socialnetwork.data.repository.authentication.AuthenticationRepository
import kotlinx.coroutines.flow.last
import javax.inject.Inject

@ExperimentalPagingApi
class PostsRemoteMediator @Inject constructor(
    private val socialService: SocialService,
    private val socialDatabase: SocialDatabase,
    private val authenticationRepository: AuthenticationRepository,
): RemoteMediator<Int, PostEntity>() {
    private val postsDao = socialDatabase.postsDao()
    private val remoteKesDao = socialDatabase.remoteKeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PostEntity>
    ): MediatorResult {
        return try {
            val currentPage = when(loadType) {
                LoadType.REFRESH -> {
                    val remoteKey = getRemoteKeyClosestToCurrentPosition(state)
                    Log.i("PAGING","refrishing ${remoteKey?.nextPage}")

                    remoteKey?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKey = getRemoteKeyForFirstItem(state)
                    Log.i("PAGING","prepending ${remoteKey?.previousPage}")
                    remoteKey?.previousPage ?: return MediatorResult.Success(remoteKey != null)
                }
                LoadType.APPEND -> {
                    val remoteKey = getRemoteKeyForLastItem(state)
                    Log.i("PAGING","appending ${remoteKey?.nextPage}")
                    remoteKey?.nextPage ?: return MediatorResult.Success(remoteKey == null)
                }
            }

            val userId = authenticationRepository.getUserId()
            val response = socialService.viewNewsFeed(userId.last() ?: -1, currentPage)
            val endOfPaginationReached = response.result.posts.isEmpty()
            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            Log.i("PAGING","prevPage $prevPage , currentPage $currentPage, nextPage $nextPage is end $endOfPaginationReached ")
            socialDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    postsDao.deleteAllPosts()
                    remoteKesDao.deleteAllRemoteKeys()
                }
                val remoteKeys = response.result.posts.map {
                    RemoteKeyEntity(
                        id = it.details?.postId ?: 0,
                        previousPage = prevPage,
                        nextPage = nextPage
                    )
                }
                remoteKesDao.insertRemoteKeys(remoteKeys)
                postsDao.insertPosts(response.result.posts.map { it.toPostEntity() })
            }
            MediatorResult.Success(endOfPaginationReached)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, PostEntity>): RemoteKeyEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                remoteKesDao.getRemoteKeyById(id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, PostEntity>): RemoteKeyEntity? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()?.let { postEntity -> remoteKesDao.getRemoteKeyById(postEntity.id) }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, PostEntity>): RemoteKeyEntity? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()?.let { postEntity ->
            Log.i("PAGING",remoteKesDao.getRemoteKeyById(postEntity.id).toString() +  " reomote key")
            remoteKesDao.getRemoteKeyById(postEntity.id) }
    }
}