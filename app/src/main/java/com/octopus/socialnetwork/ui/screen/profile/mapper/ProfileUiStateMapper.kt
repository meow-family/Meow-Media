package com.octopus.socialnetwork.ui.screen.profile.mapper

import com.octopus.socialnetwork.domain.model.post.Post
import com.octopus.socialnetwork.domain.model.user.UserDetails
import com.octopus.socialnetwork.ui.screen.profile.uistate.ProfilePostUiState
import com.octopus.socialnetwork.ui.screen.profile.uistate.ProfileUiState


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