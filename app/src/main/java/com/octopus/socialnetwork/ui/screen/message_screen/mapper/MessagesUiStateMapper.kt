package com.octopus.socialnetwork.ui.screen.message_screen.mapper

import com.octopus.socialnetwork.domain.model.messages.MessageDetails
import com.octopus.socialnetwork.ui.screen.message_screen.uistate.RecentMessagesUiState

fun MessageDetails.toMessageUiStateMapper(): RecentMessagesUiState {
    return RecentMessagesUiState(
        idOfSender = userId,
        nameOfSender = messageSender.fullName,
        message = message,
        timeOfLastSend = time,
        viewed = viewed,
        avatar = messageReceiver.avatar,
        )
}