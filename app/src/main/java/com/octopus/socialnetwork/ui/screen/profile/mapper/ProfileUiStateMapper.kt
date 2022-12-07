package com.octopus.socialnetwork.ui.screen.profile.mapper

import com.octopus.socialnetwork.domain.model.post.Post
import com.octopus.socialnetwork.domain.model.user.UserDetails
import com.octopus.socialnetwork.ui.screen.profile.uistate.ProfilePostUiState
import com.octopus.socialnetwork.ui.screen.profile.uistate.ProfileUiState


fun UserDetails.toProfileUiState(): ProfileUiState {
    return ProfileUiState(
        fullName = fullName,
        username = username,
        profileAvatar = avatar,
        profileCover = coverUrl,
    )
}

fun Post.toProfilePostUiState(): ProfilePostUiState {
    return ProfilePostUiState(
        postId = postId,
        postOwnerId = ownerId,
        postImage = image
    )
}

fun List<Post>.toProfilePostsUiState(): List<ProfilePostUiState> = this.map { it.toProfilePostUiState() }