package com.octopus.socialnetwork.ui.screen.message_screen.uistate

data class MessageMainUiState(
    val isFail: Boolean = false,
    val isLoading: Boolean = true,
    val messages: MessageUiState = MessageUiState(),
    val recentMessages: List<RecentMessagesUiState> = emptyList()

)