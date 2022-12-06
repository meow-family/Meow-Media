package com.octopus.socialnetwork.domain.usecase.message

import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.user.asUserDetails
import com.octopus.socialnetwork.domain.model.messages.messages_recent_list.MessageDetails
import com.octopus.socialnetwork.domain.model.user.UserDetails
import javax.inject.Inject

class FetchMessagesListUseCase @Inject constructor(
    private val socialRepository: SocialRepository,
) {
    suspend operator fun invoke(messageSenderId: Int, messageReceiverId: Int): MessageDetails {
        return socialRepository.getMessagesList(messageSenderId, messageReceiverId)
    }

}