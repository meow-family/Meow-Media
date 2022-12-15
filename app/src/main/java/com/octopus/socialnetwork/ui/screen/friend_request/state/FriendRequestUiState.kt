package com.octopus.socialnetwork.ui.screen.friend_request.state

import com.octopus.socialnetwork.ui.screen.profile.uistate.UserDetailsUiState


data class FriendRequestUiState(
    val friendRequests: List<UserDetailsUiState> = emptyList(),
    val isLoading: Boolean = true,
    val isError: Boolean = false,
)