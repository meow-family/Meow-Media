package com.octopus.socialnetwork.domain.usecase.messages

import com.octopus.socialnetwork.data.repository.messaging.MessagingRepository
import com.octopus.socialnetwork.domain.mapper.messages.toMessageDetails
import com.octopus.socialnetwork.domain.model.messages.MessageDetails
import com.octopus.socialnetwork.domain.usecase.user.FetchUserIdUseCase
import javax.inject.Inject

class SendMessagesUseCase @Inject constructor(
    private val socialRepository: MessagingRepository,
    private val fetchUserId: FetchUserIdUseCase
) {
    suspend operator fun invoke( to: Int, message: String): MessageDetails {
        return socialRepository.sendMessage(fetchUserId(), to, message).toMessageDetails(fetchUserId())
    }
}