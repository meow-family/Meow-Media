package com.octopus.socialnetwork.domain.usecase.messages.conversations

import com.octopus.socialnetwork.data.repository.messaging.MessagingRepository
import com.octopus.socialnetwork.domain.mapper.messages.toMessage
import com.octopus.socialnetwork.domain.model.messages.Messages
import com.octopus.socialnetwork.domain.usecase.authentication.FetchUserIdUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetRecentMessagesListUseCase @Inject constructor(
    private val messagingRepository: MessagingRepository,
) {
    suspend operator fun invoke(): Flow<List<Messages>> {
        return messagingRepository.getAllConversations().map { messages ->
            messages.map { it.toMessage() }
        }
    }
}
class CacheMessagesUseCase @Inject constructor(
    private val fetchUserIdUseCase: FetchUserIdUseCase,
    private val messagingRepository: MessagingRepository,
) {
    suspend operator fun invoke() : Boolean {
        val userId = fetchUserIdUseCase()
        messagingRepository.insertConversations(userId)
        return true
    }

}

