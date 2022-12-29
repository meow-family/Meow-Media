package com.octopus.socialnetwork.ui.screen.messaging.chat.state


data class ChatUiState(
    val lastMessage: String = "",
    val lastSendTime: String = "",
    val unreadMessagesCount: String ="",
    val viewed: String = "",
    val message: String = "",
    val isSentByMe: Boolean = true,
)
