package com.octopus.socialnetwork.ui.screen.post.mapper

import com.octopus.socialnetwork.domain.model.post.Post
import com.octopus.socialnetwork.ui.screen.post.uistate.PostUiState

fun Post.asPostUiState(): PostUiState {
    return PostUiState(
        userName = username,
        fullName = fullName,
        profileAvatar = avatar,
        postImage = image,
        postDescription = description,
        likeCount = totalLikes.toString(),
        commentCount = totalComments.toString()
    )
}