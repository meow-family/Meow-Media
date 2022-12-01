package com.octopus.socialnetwork.data.repository.post

import com.octopus.socialnetwork.data.remote.response.dto.base.BaseResponse
import com.octopus.socialnetwork.data.remote.response.dto.post.PostDTO
import com.octopus.socialnetwork.data.remote.response.dto.user.UserPostsDTO
import com.octopus.socialnetwork.data.remote.service.SocialService
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val service: SocialService,
): PostRepository {
    override suspend fun getUserPosts(
        visitedUserId: Int,
        currentUserId: Int,
    ): BaseResponse<UserPostsDTO> {
        return service.getUserPosts(
            visitedUserId,
            currentUserId,
        )
    }

    override suspend fun viewPost(postId: Int, userId: Int): BaseResponse<PostDTO> {
        return service.viewPost(
            postId,
            userId,
        )
    }

    override suspend fun viewUserPosts(ownerId: Int, viewerId: Int): List<BaseResponse<PostDTO>> {
        return service.viewUserPosts(
            ownerId,
            viewerId,
        )
    }

    override suspend fun viewNewsFeed(userId: Int): List<BaseResponse<PostDTO>> {
        return service.viewNewsFeed(
            userId,
        )
    }

    override suspend fun createPost(): BaseResponse<PostDTO> {
        return service.createPost()
    }

    override suspend fun deletePost(postId: Int, userId: Int): BaseResponse<PostDTO> {
        return service.deletePost(
            postId,
            userId,
        )
    }
}