package com.octopus.socialnetwork.ui.screen.messaging.chat.uistate

data class ChatMainUiState(
    val isFail: Boolean = false,
    val isLoading: Boolean = true,
    val isSuccess: Boolean = false,
    val messages: List<ChatUiState> = emptyList(),
    val userId: Int = 0,
    val message: String = "",
    val fullName: String = "",
    val profileAvatar: String = "",
)