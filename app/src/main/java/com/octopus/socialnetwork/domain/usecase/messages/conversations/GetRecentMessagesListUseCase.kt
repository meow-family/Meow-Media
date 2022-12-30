package com.octopus.socialnetwork.domain.usecase.messages.conversations

import com.octopus.socialnetwork.data.repository.messaging.MessagingRepository
import com.octopus.socialnetwork.domain.mapper.messages.toMessage
import com.octopus.socialnetwork.domain.model.messages.Messages
import com.octopus.socialnetwork.domain.usecase.authentication.FetchUserIdUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class GetRecentMessagesListUseCase @Inject constructor(
    private val messagingRepository: MessagingRepository,
    private val fetchUserIdUseCase: FetchUserIdUseCase,
) {
    suspend operator fun invoke(): Flow<List<Messages>> {
        val userId = fetchUserIdUseCase()

        messagingRepository.insertConversations(userId)

        return messagingRepository.getAllConversations().flatMapConcat { messagesEntity ->
            flowOf(messagesEntity.map { it.toMessage() })
        }
    }
}