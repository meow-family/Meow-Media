package com.octopus.socialnetwork.ui.screen.chat.uistate

data class MessageUiState(
    val senderId: Int = 0,
    val senderName: String = "",
    val lastSendTime: Long = 0,
    val avatar: String = "",
    val viewed: String = "",
    val message: String = "",
)
