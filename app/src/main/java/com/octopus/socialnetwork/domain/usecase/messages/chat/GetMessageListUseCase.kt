package com.octopus.socialnetwork.domain.usecase.messages.chat

import androidx.paging.PagingData
import androidx.paging.map
import com.octopus.socialnetwork.data.repository.messaging.MessagingRepository
import com.octopus.socialnetwork.domain.mapper.messages.toMessages
import com.octopus.socialnetwork.domain.model.messages.Messages
import com.octopus.socialnetwork.domain.usecase.authentication.FetchUserIdUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetMessageListUseCase @Inject constructor(
    private val messagingRepository: MessagingRepository,
    private val fetchUserIdUseCase: FetchUserIdUseCase,
) {
    suspend operator fun invoke(friendId: Int): Flow<PagingData<Messages>> {
        val userId = fetchUserIdUseCase()

        return messagingRepository.getMessagesPager(friendId).flow.map { pagerMessages ->
            pagerMessages.map { it.toMessages(userId)
            }
        }
    }
}