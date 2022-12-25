package com.octopus.socialnetwork.ui.screen.conversations.messages.uistate

import com.octopus.socialnetwork.ui.screen.profile.uistate.UserDetailsUiState

data class MessageMainUiState(
    val isFail: Boolean = false,
    val isLoading: Boolean = true,
    val isSuccess: Boolean = false,
    val messages: List<MessageUiState> = emptyList(),
    val lastMessage: String = "",
    val query: String = "",
    val isRefreshing: Boolean = false,
    val users: List<UserDetailsUiState> = emptyList(),
    var isSearchVisible: Boolean = false,
)