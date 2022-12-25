package com.octopus.socialnetwork.ui.screen.messaging.conversations.uistate

import com.octopus.socialnetwork.ui.screen.profile.uistate.UserDetailsUiState

data class ConversationsMainUiState(
    val isFail: Boolean = false,
    val isLoading: Boolean = true,
    val isSuccess: Boolean = false,
    val messages: List<ConversationUiState> = emptyList(),
    val lastMessage: String = "",
    val query: String = "",
    val users: List<UserDetailsUiState> = emptyList(),
    var isSearchVisible: Boolean = false,
)