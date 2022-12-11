package com.octopus.socialnetwork.domain.usecase.messages.chat

import com.octopus.socialnetwork.data.repository.messaging.MessagingRepository
import com.octopus.socialnetwork.domain.mapper.messages.toMessageDetails
import com.octopus.socialnetwork.domain.model.messages.MessageDetails
import javax.inject.Inject

class GetMessageListUseCase @Inject constructor(
    private val socialRepository: MessagingRepository
) {
    suspend operator fun invoke(currentUserId:Int,otherUserId:Int): List<MessageDetails> {
        return socialRepository.getMessages(currentUserId, otherUserId).messages?.map { it.toMessageDetails() }
            ?: emptyList()
    }
}