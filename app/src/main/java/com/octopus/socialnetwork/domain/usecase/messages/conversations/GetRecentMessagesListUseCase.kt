package com.octopus.socialnetwork.domain.usecase.messages.conversations

import com.octopus.socialnetwork.data.repository.messaging.MessagingRepository
import com.octopus.socialnetwork.domain.mapper.messages.toMessages
import com.octopus.socialnetwork.domain.mapper.messages.toConversationsEntity
import com.octopus.socialnetwork.domain.model.messages.Messages
import com.octopus.socialnetwork.domain.usecase.authentication.FetchUserIdUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetRecentMessagesListUseCase @Inject constructor(
    private val messagingRepository: MessagingRepository,
    private val fetchUserIdUseCase: FetchUserIdUseCase,
) {
    suspend operator fun invoke(): Flow<List<Messages>> {
        val userId = fetchUserIdUseCase()

        val response = messagingRepository.getRecentMassagesList(userId)

        messagingRepository.insertConversations(response.messages?.map {
            it.toConversationsEntity(userId)
        } ?: emptyList())


        val messages = response.messages?.map {
            it.toMessages(userId)
        }

        return messagingRepository.getAllConversations().map { it.map { it.toMessages() } }

    }
}