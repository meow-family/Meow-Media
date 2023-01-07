package com.octopus.socialnetwork.domain.usecase.messages.chat

import com.octopus.socialnetwork.data.repository.messaging.MessagingRepository
import com.octopus.socialnetwork.domain.mapper.comments.toComment
import com.octopus.socialnetwork.domain.mapper.messages.toMessages
import com.octopus.socialnetwork.domain.model.messages.Messages
import com.octopus.socialnetwork.domain.usecase.authentication.FetchUserIdUseCase
import javax.inject.Inject

class GetMessageListUseCase @Inject constructor(
    private val messagingRepository: MessagingRepository,
    private val fetchUserIdUseCase: FetchUserIdUseCase,
) {
    private var page = 1
    suspend operator fun invoke(friendId: Int): List<Messages> {
        val userId = fetchUserIdUseCase()
        val result = messagingRepository.getMessages(userId, friendId,page).messages?.reversed()
        return if (result?.isNotEmpty() == true) {
            page += 1
            result.map { it.toMessages(userId) }
        } else {
            emptyList()
        }
    }
}