package com.octopus.socialnetwork.ui.screen.chat.uistate

data class MessageMainUiState(
    val isFail: Boolean = false,
    val isLoading: Boolean = true,
    var unreadMessagesCount: Int = 0,
    val messages: List<MessageUiState> = emptyList(),
    val lastMessage: String = "",
    val query: String = "",
)