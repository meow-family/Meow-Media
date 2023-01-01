package com.octopus.socialnetwork.data.paging

import android.util.Log
import com.octopus.socialnetwork.data.remote.response.dto.post.PostDto
import com.octopus.socialnetwork.data.remote.service.apiService.SocialService
import com.octopus.socialnetwork.data.repository.authentication.AuthenticationRepository
import javax.inject.Inject
import kotlin.properties.Delegates

class PostDataSource @Inject constructor(
    private val service: SocialService,
    private val authenticationRepository: AuthenticationRepository,
) : BasePagingSource<PostDto>() {

    private var userId by Delegates.notNull<Int>()


    fun setUserID(userID: Int) {
        userId = userID
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PostDto> {
        val pageNumber = params.key ?: 1
        return try {
            val response = authenticationRepository.getUserId()
                ?.let { service.getUserPosts(userId,it,pageNumber) }
            Log.e("TESTING","IN DATA SOURSE POST:${response?.result} ")
            LoadResult.Page(
                data = response?.result?.posts ?: emptyList(),
                prevKey = null ,
                nextKey =if (response?.result?.posts?.isNotEmpty() == true)
                  response.result.offset.plus(1) else null
            )
        } catch (e: Throwable) {
            Log.e("TESTING","IN DATA SOURSE POST faild:${e.message} ")

            LoadResult.Error(e)
        }
    }
}