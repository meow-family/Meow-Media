package com.octopus.socialnetwork.ui.screen.messaging.conversations.mapper

import com.octopus.socialnetwork.domain.model.messages.Messages
import com.octopus.socialnetwork.domain.model.messages.MessageUser
import com.octopus.socialnetwork.ui.screen.messaging.conversations.uistate.ConversationUiState
import com.octopus.socialnetwork.ui.screen.profile.uistate.UserDetailsUiState
import com.octopus.socialnetwork.ui.util.extensions.getHourAndMinutes
import com.octopus.socialnetwork.ui.util.extensions.removeHtmlEncoding

fun Messages.toConversationUiState(): ConversationUiState {
    return ConversationUiState(
        otherUser = otherUser.toUserUiState(),
        lastMessage = this.message.removeHtmlEncoding(),
        lastSendTime = time.getHourAndMinutes(),
        isSentByMe = isSentByMe,
    )
}

fun MessageUser.toUserUiState(): UserDetailsUiState {
    return UserDetailsUiState(
        userId = userId,
        fullName = fullName,
        profileAvatar = avatar,
    )
}
