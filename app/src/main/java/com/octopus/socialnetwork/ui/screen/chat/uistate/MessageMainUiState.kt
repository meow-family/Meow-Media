package com.octopus.socialnetwork.ui.screen.chat.uistate

data class MessageMainUiState(
    val isFail: Boolean = false,
    val isLoading: Boolean = true,
    val isSuccess: Boolean = false,
    var unreadMessagesCount: Int = 0,
    val messages: List<MessageUiState> = emptyList(),
    val lastMessage: String = "",
    val message: String = "",
    val query: String = "",
    val isRefreshing: Boolean = false,
)