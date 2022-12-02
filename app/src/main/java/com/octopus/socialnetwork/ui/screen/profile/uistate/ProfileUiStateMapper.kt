package com.octopus.socialnetwork.ui.screen.profile.uistate

import com.octopus.socialnetwork.domain.model.user_details.UserDetails

fun UserDetails.asProfileUiState(friendsCount: Int, postsCount: Int): ProfileUiState {
    return ProfileUiState(
        fullName = fullName,
        username = username,
        friendsCount = friendsCount.toString(),
        postCount = postsCount.toString(),
        profileAvatar = avatar,
        profileCover = coverUrl
    )
}