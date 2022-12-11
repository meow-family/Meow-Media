package com.octopus.socialnetwork.domain.usecase.messages

import com.octopus.socialnetwork.data.repository.messaging.MessagingRepository
import com.octopus.socialnetwork.domain.mapper.messages.toMessageDetails
import com.octopus.socialnetwork.domain.model.messages.MessageDetails
import javax.inject.Inject

class GetRecentMessagesListUseCase @Inject constructor(
    private val socialRepository: MessagingRepository
) {
    suspend operator fun invoke(userId: Int): List<MessageDetails> {
        val recentMessage =
            socialRepository.getRecentMassagesList(userId).messages?.map { it.toMessageDetails() }
        return changeData(userId, recentMessage!!)
    }

    private fun changeData(
        userId: Int,
        messages: List<MessageDetails>?
    ): List<MessageDetails> {

        return messages!!.filter {
            it.messageSender.userId != userId
        }
    }
}