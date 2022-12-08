package com.octopus.socialnetwork.domain.usecase.messages

import com.octopus.socialnetwork.data.repository.messaging.MessagingRepository
import com.octopus.socialnetwork.domain.mapper.messages.asUnreadMassages
import com.octopus.socialnetwork.domain.model.messages.UnreadMessageDetails
import javax.inject.Inject

class UnreadMessagesUseCase @Inject constructor(
    private val socialRepository: MessagingRepository
) {
    suspend operator fun invoke(from: Int, to: Int, unreadMessage: String): UnreadMessageDetails {
        return socialRepository.unreadMessages(from, to, unreadMessage).asUnreadMassages()
    }
}