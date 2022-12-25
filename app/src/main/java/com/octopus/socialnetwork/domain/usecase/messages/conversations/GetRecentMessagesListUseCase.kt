package com.octopus.socialnetwork.domain.usecase.messages.conversations

import com.octopus.socialnetwork.data.repository.messaging.MessagingRepository
import com.octopus.socialnetwork.domain.mapper.messages.toMessageDetails
import com.octopus.socialnetwork.domain.model.messages.MessageDetails
import com.octopus.socialnetwork.domain.usecase.authentication.FetchUserIdUseCase
import javax.inject.Inject

class GetRecentMessagesListUseCase @Inject constructor(
    private val socialRepository: MessagingRepository,
    private val fetchUserIdUseCase: FetchUserIdUseCase,
) {
    suspend operator fun invoke(): List<MessageDetails> {
        val userId = fetchUserIdUseCase()

        val response = socialRepository.getRecentMassagesList(userId)
        val messages = response.messages?.map {
            it.toMessageDetails(userId)
        }
        return messages ?: emptyList()
    }
}