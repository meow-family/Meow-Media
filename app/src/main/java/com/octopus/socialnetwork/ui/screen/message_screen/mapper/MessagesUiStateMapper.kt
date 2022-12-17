package com.octopus.socialnetwork.ui.screen.message_screen.mapper

import com.octopus.socialnetwork.domain.model.messages.MessageDetails
import com.octopus.socialnetwork.ui.screen.message_screen.uistate.MessageUiState
import com.octopus.socialnetwork.ui.util.extensions.getHourAndMinutes

fun MessageDetails.toMessageUiState(): MessageUiState {
    return MessageUiState(
        senderId = messageSender.userId,
        senderName = messageSender.fullName,
        message = message,
        lastSendTime = time.getHourAndMinutes(),
        viewed = viewed,
        avatar = messageSender.avatar,
        isSentByMe = isSentByMe,
        )
}

