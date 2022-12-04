package com.octopus.socialnetwork.ui.screen.profile.uistate

import com.octopus.socialnetwork.domain.model.post.Post
import com.octopus.socialnetwork.domain.model.user.UserDetails


fun UserDetails.asProfileUiState(): ProfileUiState {
    return ProfileUiState(
        fullName = fullName,
        username = username,
        profileAvatar = avatar,
        profileCover = coverUrl,
    )
}

fun Post.asProfilePostUiState(): ProfilePostUiState {
    return ProfilePostUiState(
        postId = postId,
        postImage = image
    )
}

fun List<Post>.asProfilePostsUiState(): List<ProfilePostUiState> = this.map { it.asProfilePostUiState() }