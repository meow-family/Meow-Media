package com.octopus.socialnetwork.ui.screen.messaging.conversations.uistate

import com.octopus.socialnetwork.ui.screen.profile.state.UserDetailsUiState

data class ConversationUiState(
    val lastMessage: String = "",
    val lastSendTime: String = "",
    val isSentByMe: Boolean = true,
    val otherUser: UserDetailsUiState = UserDetailsUiState(),
)
