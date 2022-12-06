package com.octopus.socialnetwork.domain.usecase.message

import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.message.toUnreadMessages
import com.octopus.socialnetwork.domain.model.messages.messages_recent_list.UnreadMessageDetails
import javax.inject.Inject

class FetchUnreadMessagesUseCase @Inject constructor(
    private val socialRepository: SocialRepository,
) {
    suspend operator fun invoke(
        messageSenderId: Int,
        messageReceiverId: Int,
        markAllRead: String
    ): UnreadMessageDetails? {
        return socialRepository.unreadMessages(messageSenderId, messageReceiverId, markAllRead)
            .toUnreadMessages()
    }

}
