package com.octopus.socialnetwork.domain.usecase.messages

import com.octopus.socialnetwork.data.repository.messaging.MessagingRepository
import com.octopus.socialnetwork.domain.mapper.messages.asSendMassagesList
import com.octopus.socialnetwork.domain.model.messages.MessageDetails
import javax.inject.Inject

class SendMessagesUseCase @Inject constructor(
    private val socialRepository: MessagingRepository
) {
    suspend operator fun invoke(from: Int, to: Int, message: String): MessageDetails {
        return socialRepository.sendMessage(from, to, message).asSendMassagesList()
    }
}