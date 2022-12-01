package com.octopus.socialnetwork.data.repository.post

import com.octopus.socialnetwork.data.remote.response.dto.base.BaseResponse
import com.octopus.socialnetwork.data.remote.response.dto.post.PostDTO
import com.octopus.socialnetwork.data.remote.response.dto.user.UserPostsDTO

interface PostRepository {
    suspend fun getUserPosts(visitedUserId: Int, currentUserId: Int): BaseResponse<UserPostsDTO>

    suspend fun viewPost(postId: Int, userId: Int): BaseResponse<PostDTO>

    suspend fun viewUserPosts(ownerId: Int, viewerId: Int): List<BaseResponse<PostDTO>>

    suspend fun viewNewsFeed(userId: Int): List<BaseResponse<PostDTO>>

    suspend fun createPost(): BaseResponse<PostDTO>

    suspend fun deletePost(postId: Int, userId: Int): BaseResponse<PostDTO>
}