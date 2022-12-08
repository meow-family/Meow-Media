package com.octopus.socialnetwork.domain.usecase.messages

import com.octopus.socialnetwork.data.repository.messaging.MessagingRepository
import com.octopus.socialnetwork.domain.mapper.messages.asMassagesDetails
import com.octopus.socialnetwork.domain.model.messages.MessageDetails
import javax.inject.Inject

class MessageListUseCase @Inject constructor(
    private val socialRepository: MessagingRepository
) {
    suspend operator fun invoke(from: Int, to: Int): List<MessageDetails> {
        return socialRepository.messageList(from, to).messages?.map { it.asMassagesDetails() }
            ?: emptyList()
    }
}