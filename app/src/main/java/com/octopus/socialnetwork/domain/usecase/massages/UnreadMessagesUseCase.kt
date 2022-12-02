package com.octopus.socialnetwork.domain.usecase.massages

import com.octopus.socialnetwork.data.repository.messaging.MessagingRepository
import com.octopus.socialnetwork.domain.mapper.massages.asUnreadMassages
import com.octopus.socialnetwork.domain.model.massages.messagesRecentList.UnreadMessageDetails
import javax.inject.Inject

class UnreadMessagesUseCase @Inject constructor(
    private val socialRepository: MessagingRepository
) {
    suspend operator fun invoke(from: Int, to: Int, unreadMessage: String): UnreadMessageDetails {
        return socialRepository.unreadMessages(from, to, unreadMessage).asUnreadMassages()
    }
}