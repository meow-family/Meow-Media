package com.octopus.socialnetwork.domain.usecase.messages

import com.octopus.socialnetwork.data.repository.messaging.MessagingRepository
import com.octopus.socialnetwork.domain.mapper.messages.toMessageDetails
import com.octopus.socialnetwork.domain.model.messages.MessageDetails
import com.octopus.socialnetwork.domain.usecase.user.FetchUserIdUseCase
import javax.inject.Inject

class SendMessagesUseCase @Inject constructor(
    private val socialRepository: MessagingRepository,
    private val fetchUserIdUseCase: FetchUserIdUseCase
) {
    suspend operator fun invoke(from: Int, to: Int, message: String): MessageDetails {
        return socialRepository.sendMessage(from, to, message).toMessageDetails(fetchUserIdUseCase())
    }
}