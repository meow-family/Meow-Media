package com.octopus.socialnetwork.ui.screen.post.mapper

import com.octopus.socialnetwork.domain.model.post.Post
import com.octopus.socialnetwork.ui.screen.post.uistate.PostUiState

fun Post.toPostUiState(): PostUiState {
    return PostUiState(
        postId = postId,
        ownerId = ownerId,
        userName = username,
        fullName = fullName,
        profileAvatar = avatar,
        postImage = image,
        postDescription = description,
        likeCount = totalLikes.toString(),
        commentCount = totalComments.toString(),
        postDate = timeCreated,
        isLiked = isLikedByUser
    )
}