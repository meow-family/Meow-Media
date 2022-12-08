package com.octopus.socialnetwork.domain.usecase.messages

import com.octopus.socialnetwork.data.repository.messaging.MessagingRepository
import com.octopus.socialnetwork.domain.mapper.messages.asMassagesDetails
import com.octopus.socialnetwork.domain.model.messages.MessageDetails
import javax.inject.Inject

class GetMessagesListRecentUseCase @Inject constructor(
    private val socialRepository: MessagingRepository
) {
    suspend operator fun invoke(userId:Int): List<MessageDetails>? {
        return socialRepository.getRecentMassagesList(userId).messages?.map { it.asMassagesDetails() }
    }
}