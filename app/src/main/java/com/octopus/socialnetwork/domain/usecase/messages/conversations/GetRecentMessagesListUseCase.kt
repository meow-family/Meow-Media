package com.octopus.socialnetwork.domain.usecase.messages.conversations

import com.octopus.socialnetwork.data.repository.messaging.MessagingRepository
import com.octopus.socialnetwork.domain.mapper.messages.toMessages
import com.octopus.socialnetwork.domain.model.messages.Messages
import com.octopus.socialnetwork.domain.usecase.authentication.FetchUserIdUseCase
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class GetRecentMessagesListUseCase @Inject constructor(
    private val socialRepository: MessagingRepository,
    private val fetchUserIdUseCase: FetchUserIdUseCase,
) {
    suspend operator fun invoke(): List<Messages> {
        val userId = fetchUserIdUseCase().first()

        val response = socialRepository.getRecentMassagesList(userId)
        val messages = response.messages?.map {
            it.toMessages(userId)
        }
        return messages ?: emptyList()
    }
}