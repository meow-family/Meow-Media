package com.octopus.socialnetwork.ui.screen.messaging.chat.state

data class ChatMainUiState(
    val isFail: Boolean = false,
    val isLoading: Boolean = true,
    val isSuccess: Boolean = false,
    val messages: List<ChatUiState> = emptyList(),
    val userId: Int = 0,
    val message: String = "",
    val fullName: String = "",
    val profileAvatar: String = "",
    val isPagerLoading: Boolean = false,
    val isEndOfPager: Boolean = false,
    val error: String = "",
    val pagerError: String = "",
)