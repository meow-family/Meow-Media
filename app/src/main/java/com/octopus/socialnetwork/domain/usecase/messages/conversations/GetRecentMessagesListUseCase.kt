package com.octopus.socialnetwork.domain.usecase.messages.conversations

import com.octopus.socialnetwork.data.repository.messaging.MessagingRepository
import com.octopus.socialnetwork.domain.mapper.messages.entityToMessages
import com.octopus.socialnetwork.domain.model.messages.Messages
import com.octopus.socialnetwork.domain.usecase.authentication.FetchUserIdUseCase
import javax.inject.Inject

class GetRecentMessagesListUseCase @Inject constructor(
    private val messagingRepository: MessagingRepository,
    private val fetchUserIdUseCase: FetchUserIdUseCase,
) {
    suspend operator fun invoke(): List<Messages> {
        val userId = fetchUserIdUseCase()

        messagingRepository.insertConversations(userId)

        return messagingRepository.getAllConversations().map { it.entityToMessages() }
    }
}