package com.octopus.socialnetwork.ui.screen.chat.mapper

import com.octopus.socialnetwork.domain.model.messages.MessageDetails
import com.octopus.socialnetwork.domain.model.messages.MessageUser
import com.octopus.socialnetwork.ui.screen.chat.uistate.MessageUiState
import com.octopus.socialnetwork.ui.screen.profile.uistate.UserDetailsUiState
import com.octopus.socialnetwork.ui.util.extensions.getHourAndMinutes

fun MessageDetails.toMessageUiState(): MessageUiState {
    return MessageUiState(
        message = this.message,
        otherUser = otherUser.toUserUiState(),
        lastMessage = this.message,
        lastSendTime = time.getHourAndMinutes(),
        viewed = viewed,
        unreadMessagesCount = "0",
        isSentByMe = isSentByMe
    )
}

fun MessageUser.toUserUiState(): UserDetailsUiState {
    return UserDetailsUiState(
        userId = userId,
        fullName = fullName,
        profileAvatar = avatar,
    )
}
