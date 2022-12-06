package com.octopus.socialnetwork.domain.usecase.message

import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.message.toRecentMessages
import com.octopus.socialnetwork.domain.model.messages.messages_recent_list.RecentMessagesList
import javax.inject.Inject

class FetchRecentMessagesUseCase @Inject constructor(
    private val socialRepository: SocialRepository,
) {
    suspend operator fun invoke(userId: Int): RecentMessagesList {
        return socialRepository.getMessagesListRecent(userId).toRecentMessages()
    }

}