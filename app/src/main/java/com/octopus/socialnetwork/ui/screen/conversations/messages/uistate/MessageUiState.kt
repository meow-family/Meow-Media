package com.octopus.socialnetwork.ui.screen.conversations.messages.uistate

import com.octopus.socialnetwork.ui.screen.profile.uistate.UserDetailsUiState

data class MessageUiState(
    val lastMessage: String = "",
    val lastSendTime: String = "",
    val message: String = "",
    val isSentByMe: Boolean = true,
    val otherUser: UserDetailsUiState = UserDetailsUiState(),
)
