package com.octopus.socialnetwork.domain.usecase.massages

import com.octopus.socialnetwork.data.repository.messaging.MessagingRepository
import com.octopus.socialnetwork.domain.mapper.massages.asRecentMassagesList
import com.octopus.socialnetwork.domain.model.massages.messagesRecentList.RecentMessagesList
import javax.inject.Inject

class GetMessagesListRecentUseCase @Inject constructor(
    private val socialRepository: MessagingRepository
) {
    suspend operator fun invoke(userId:Int): RecentMessagesList {
        return socialRepository.getMassagesList(userId).asRecentMassagesList()
    }
}