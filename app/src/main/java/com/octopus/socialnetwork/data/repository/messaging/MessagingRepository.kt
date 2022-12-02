package com.octopus.socialnetwork.data.repository.messaging

import com.octopus.socialnetwork.data.remote.response.dto.massages.RecentmessagesListDto.RecentMessagesListDTO

interface MessagingRepository {
    suspend fun getMassagesList(visitedUserId: Int): RecentMessagesListDTO

}