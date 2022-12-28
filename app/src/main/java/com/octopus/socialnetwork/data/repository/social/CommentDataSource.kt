package com.octopus.socialnetwork.data.repository.social

import com.octopus.socialnetwork.data.paging.BasePagingSource
import com.octopus.socialnetwork.data.remote.response.dto.comment.CommentDto
import com.octopus.socialnetwork.data.remote.service.apiService.SocialService
import com.octopus.socialnetwork.data.repository.authentication.AuthenticationRepository
import javax.inject.Inject
import kotlin.properties.Delegates

class CommentDataSource @Inject constructor(
    private val service: SocialService,
    private val authenticationRepository: AuthenticationRepository,
) : BasePagingSource<CommentDto>() {

    private var postId by Delegates.notNull<Int>()


    fun setCommentID(post: Int) {
        postId = post
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CommentDto> {
        val pageNumber = params.key ?: 1
        return try {
            val response =
                authenticationRepository.getUserId()?.let { service.getCommentsList(it,postId,"post",pageNumber) }
            LoadResult.Page(
                data = response?.result?.comments ?: emptyList(),
                prevKey = null ,
                nextKey =if (response?.result?.comments?.isNotEmpty() == true) response.result.offset + 1 else null
            )
        } catch (e: Throwable) {
            LoadResult.Error(e)
        }
    }
}