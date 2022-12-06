package com.octopus.socialnetwork.ui.screen.post.mapper

import com.octopus.socialnetwork.domain.model.post.Post
import com.octopus.socialnetwork.ui.screen.post.uistate.PostUiState

fun Post.toPostUiState(): PostUiState {
    return PostUiState(
        userName = username,
        fullName = fullName,
        profileAvatar = avatar,
        postImage = image,
        postDescription = description,
        likeCount = totalLikes.toString(),
        commentCount = totalComments.toString(),
        postDate = timeCreated,
    )
}