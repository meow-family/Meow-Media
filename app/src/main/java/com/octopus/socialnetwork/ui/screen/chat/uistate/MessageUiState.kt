package com.octopus.socialnetwork.ui.screen.chat.uistate

import com.octopus.socialnetwork.ui.screen.profile.uistate.UserDetailsUiState

data class MessageUiState(
    val lastMessage: String = "",
    val lastSendTime: String = "",
    val unreadMessagesCount: String ="",
    val viewed: String = "",
    val message: String = "",
    val isSentByMe: Boolean = true,
    val otherUser: UserDetailsUiState = UserDetailsUiState(),
)
