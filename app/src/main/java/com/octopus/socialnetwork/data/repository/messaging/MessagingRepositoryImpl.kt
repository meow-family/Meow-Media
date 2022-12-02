package com.octopus.socialnetwork.data.repository.messaging

import com.octopus.socialnetwork.data.remote.response.dto.massages.RecentmessagesListDto.RecentMessagesListDTO
import com.octopus.socialnetwork.data.remote.service.SocialService
import javax.inject.Inject

class MessagingRepositoryImpl @Inject constructor(
    private val service: SocialService,
) : MessagingRepository {

    override suspend fun getMassagesList(visitedUserId: Int): RecentMessagesListDTO {
        return service.getMassagesListRecent(visitedUserId).payload
    }
}

