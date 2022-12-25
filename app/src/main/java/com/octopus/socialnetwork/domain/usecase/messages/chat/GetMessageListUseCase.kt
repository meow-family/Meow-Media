package com.octopus.socialnetwork.domain.usecase.messages.chat

import com.octopus.socialnetwork.data.repository.messaging.MessagingRepository
import com.octopus.socialnetwork.domain.mapper.messages.toMessageDetails
import com.octopus.socialnetwork.domain.model.messages.MessageDetails
import com.octopus.socialnetwork.domain.usecase.authentication.FetchUserIdUseCase
import kotlinx.coroutines.flow.last
import javax.inject.Inject

class GetMessageListUseCase @Inject constructor(
    private val messagingRepository: MessagingRepository,
    private val fetchUserIdUseCase: FetchUserIdUseCase,
) {
    suspend operator fun invoke(friendId: Int): List<MessageDetails> {
        val userId = fetchUserIdUseCase()
        return messagingRepository.getMessages(
            userId,
            friendId
        ).messages?.map { it.toMessageDetails(userId) } ?: emptyList()
    }
}