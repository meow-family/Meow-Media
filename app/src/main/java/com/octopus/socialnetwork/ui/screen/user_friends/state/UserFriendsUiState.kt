package com.octopus.socialnetwork.ui.screen.user_friends.state

import com.octopus.socialnetwork.ui.screen.profile.uistate.UserDetailsUiState


data class UserFriendsUiState(
    val users: List<UserDetailsUiState> = emptyList(),
    val isLoading: Boolean = true,
    val isError: Boolean = false,
)