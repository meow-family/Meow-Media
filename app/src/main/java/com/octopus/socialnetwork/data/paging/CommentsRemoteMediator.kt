package com.octopus.socialnetwork.data.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.octopus.socialnetwork.data.local.database.SocialDatabase
import com.octopus.socialnetwork.data.local.entity.CommentEntity
import com.octopus.socialnetwork.data.local.entity.PostEntity
import com.octopus.socialnetwork.data.local.entity.RemoteKeyEntity
import com.octopus.socialnetwork.data.mapper.toCommentEntity
import com.octopus.socialnetwork.data.mapper.toPostEntity
import com.octopus.socialnetwork.data.remote.service.SocialService
import com.octopus.socialnetwork.data.repository.authentication.AuthenticationRepository
import kotlinx.coroutines.flow.last
import javax.inject.Inject

@ExperimentalPagingApi
class CommentsRemoteMediator @Inject constructor(
    private val socialService: SocialService,
    private val socialDatabase: SocialDatabase,
    private val authenticationRepository: AuthenticationRepository,
    private val postId: Int,
): RemoteMediator<Int, CommentEntity>() {
    private val commentsDao = socialDatabase.commentDao()
    private val remoteKesDao = socialDatabase.remoteKeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CommentEntity>,

    ): MediatorResult {
        return try {
            val currentPage = when(loadType) {
                LoadType.REFRESH -> {
                    val remoteKey = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKey?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKey = getRemoteKeyForFirstItem(state)
                    remoteKey?.previousPage ?: return MediatorResult.Success(remoteKey != null)
                }
                LoadType.APPEND -> {
                    val remoteKey = getRemoteKeyForLastItem(state)
                    remoteKey?.nextPage ?: return MediatorResult.Success(remoteKey == null)
                }
            }

            val userId = authenticationRepository.getUserId()
            val response = socialService.getCommentsList(userId.last() ?: -1, type = "post", postId = postId, page = currentPage)
            val endOfPaginationReached = response.result.comments.isEmpty()
            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            socialDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    commentsDao.deleteAllComment()
                    remoteKesDao.deleteAllRemoteKeys()
                }
                val remoteKeys = response.result.comments.map {
                    RemoteKeyEntity(
                        id = it.commentId ?: 0,
                        previousPage = prevPage,
                        nextPage = nextPage
                    )
                }
                remoteKesDao.insertRemoteKeys(remoteKeys)
                commentsDao.insertComments(response.result.comments.map { it.toCommentEntity() })
            }
            MediatorResult.Success(endOfPaginationReached)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, CommentEntity>): RemoteKeyEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.commentId?.let { id ->
                remoteKesDao.getRemoteKeyById(id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, CommentEntity>): RemoteKeyEntity? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()?.let{ commentEntity -> remoteKesDao.getRemoteKeyById(commentEntity.commentId) }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, CommentEntity>): RemoteKeyEntity? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()?.let { commentEntity ->
            remoteKesDao.getRemoteKeyById(commentEntity.commentId) }
    }
}