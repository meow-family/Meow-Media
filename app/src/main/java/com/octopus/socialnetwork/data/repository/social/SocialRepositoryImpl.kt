package com.octopus.socialnetwork.data.repository.social

import com.octopus.socialnetwork.data.remote.response.dto.base.BaseResponse
import com.octopus.socialnetwork.data.remote.response.dto.like.LikeDTO
import com.octopus.socialnetwork.data.remote.response.dto.post.PostDTO
import com.octopus.socialnetwork.data.remote.response.dto.user.CheckUserFriendDTO
import com.octopus.socialnetwork.data.remote.response.dto.user.UserDetailsDTO
import com.octopus.socialnetwork.data.remote.response.dto.user.UserFriendsDTO
import com.octopus.socialnetwork.data.remote.response.dto.user.UserPostsDTO
import com.octopus.socialnetwork.data.remote.service.SocialService
import javax.inject.Inject

class SocialRepositoryImpl @Inject constructor(
    private val socialService: SocialService,
) : SocialRepository {

    override suspend fun getUserDetails(visitedUserId: Int): UserDetailsDTO {
        return socialService.getUserDetails(visitedUserId).payload
    }

    override suspend fun getUserFriends(visitedUserId: Int): UserFriendsDTO {
        return socialService.getUserFriends(visitedUserId).payload
    }

    override suspend fun getUserPosts(visitedUserId: Int, currentUserId: Int): UserPostsDTO {
        return socialService.getUserPosts(visitedUserId, currentUserId).payload
    }


    override suspend fun viewPost(postId: Int, userId: Int): BaseResponse<PostDTO> {
        return socialService.viewPost(
            postId,
            userId,
        )
    }

    override suspend fun viewUserPosts(ownerId: Int, viewerId: Int): List<BaseResponse<PostDTO>> {
        return socialService.viewUserPosts(
            ownerId,
            viewerId,
        )
    }

    override suspend fun viewNewsFeed(userId: Int): List<BaseResponse<PostDTO>> {
        return socialService.viewNewsFeed(
            userId,
        )
    }

    override suspend fun createPost(): BaseResponse<PostDTO> {
        return socialService.createPost()
    }

    override suspend fun deletePost(postId: Int, userId: Int): BaseResponse<PostDTO> {
        return socialService.deletePost(
            postId,
            userId,
        )
    }

    override suspend fun like(
        userId: Int,
        contentId: Int,
        typeContent: String
    ): BaseResponse<LikeDTO> {
        return socialService.like(userId, contentId, typeContent)
    }

    override suspend fun unlike(
        userId: Int,
        contentId: Int,
        typeContent: String
    ): BaseResponse<LikeDTO> {
        return socialService.unlike(userId, contentId, typeContent)
    }

    override suspend fun checkUserFriend(
        userId: Int,
        userIdWantedToCheck: Int
    ): BaseResponse<CheckUserFriendDTO> {
        return socialService.checkUserFriend(userId, userIdWantedToCheck)
    }


}