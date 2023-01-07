package com.octopus.socialnetwork.ui.screen.messaging.chat.state


data class ChatUiState(
    val lastSendTime: String = "",
    val message: String = "",
    val isSentByMe: Boolean = true,
)
