package com.octopus.socialnetwork.domain.usecase.message

import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.message.toSendMessage
import com.octopus.socialnetwork.domain.model.messages.messages_recent_list.MessageDetails
import javax.inject.Inject

class FetchSendMessageUseCase @Inject constructor(
    private val socialRepository: SocialRepository,
) {
    suspend operator fun invoke(
        messageSenderId: Int,
        messageReceiverId: Int,
        message: String
    ): MessageDetails? {
        return socialRepository.sendMessage(messageSenderId, messageReceiverId, message)
            .toSendMessage()
    }

}