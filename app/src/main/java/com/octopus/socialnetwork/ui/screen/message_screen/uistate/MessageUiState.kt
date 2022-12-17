package com.octopus.socialnetwork.ui.screen.message_screen.uistate

data class MessageUiState(
    val senderId: Int = 0,
    val senderName: String = "",
    val lastSendTime: Long = 0,
    val avatar: String = "",
    val viewed: String = "",
    val message: String = "",
    val isSentByMe: Boolean = true
)
