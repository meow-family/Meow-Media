package com.octopus.socialnetwork.ui.screen.messaging.chat.mapper

import com.octopus.socialnetwork.domain.model.messages.Messages

import com.octopus.socialnetwork.ui.screen.messaging.chat.uistate.ChatUiState
import com.octopus.socialnetwork.ui.util.extensions.getHourAndMinutes
import com.octopus.socialnetwork.ui.util.extensions.removeHtmlEncoding

fun Messages.toChatUiState(): ChatUiState {
    return ChatUiState(
        message = message.removeHtmlEncoding(),
        lastMessage = message.removeHtmlEncoding(),
        lastSendTime = time.getHourAndMinutes(),
        viewed = viewed,
        unreadMessagesCount = "0",
        isSentByMe = isSentByMe
    )
}