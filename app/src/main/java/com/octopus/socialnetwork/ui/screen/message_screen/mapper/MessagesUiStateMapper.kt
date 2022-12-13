package com.octopus.socialnetwork.ui.screen.message_screen.mapper

import com.octopus.socialnetwork.domain.model.messages.MessageDetails
import com.octopus.socialnetwork.ui.screen.message_screen.uistate.MessageUiState

fun MessageDetails.toMessageUiState(): MessageUiState {
    return MessageUiState(
        senderId = messageSender.userId,
        senderName = messageSender.fullName,
        message = message,
        lastSendTime = time,
        viewed = viewed,
        avatar = messageSender.avatar,
        isSentByMe = isSentByMe,
        )
}

