package com.octopus.socialnetwork.domain.usecase.massages

import com.octopus.socialnetwork.data.repository.messaging.MessagingRepository
import com.octopus.socialnetwork.domain.mapper.massages.asSendMassagesList
import com.octopus.socialnetwork.domain.model.massages.messagesRecentList.MessageDetails
import javax.inject.Inject

class MessagesSendUseCase @Inject constructor(
    private val socialRepository: MessagingRepository
) {
    suspend operator fun invoke(from: Int, to: Int, message: String): MessageDetails {
        return socialRepository.sendMessage(from, to, message).asSendMassagesList()
    }
}