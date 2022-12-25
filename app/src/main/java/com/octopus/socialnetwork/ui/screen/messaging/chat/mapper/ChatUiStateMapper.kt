package com.octopus.socialnetwork.ui.screen.messaging.chat.mapper

import com.octopus.socialnetwork.domain.model.messages.MessageDetails
import com.octopus.socialnetwork.ui.screen.messaging.chat.uistate.ChatUiState
import com.octopus.socialnetwork.ui.util.extensions.getHourAndMinutes

fun MessageDetails.toChatUiState(): ChatUiState {
    return ChatUiState(
        message = this.message,
        lastMessage = this.message,
        lastSendTime = time.getHourAndMinutes(),
        viewed = viewed,
        unreadMessagesCount = "0",
        isSentByMe = isSentByMe
    )
}