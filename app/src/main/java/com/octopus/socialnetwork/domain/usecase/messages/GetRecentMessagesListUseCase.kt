package com.octopus.socialnetwork.domain.usecase.messages

import android.util.Log
import com.octopus.socialnetwork.data.repository.messaging.MessagingRepository
import com.octopus.socialnetwork.domain.mapper.messages.toMessageDetails
import com.octopus.socialnetwork.domain.model.messages.MessageDetails
import com.octopus.socialnetwork.domain.usecase.user.FetchUserIdUseCase
import javax.inject.Inject

class GetRecentMessagesListUseCase @Inject constructor(
    private val socialRepository: MessagingRepository,
    private val fetchUserIdUseCase: FetchUserIdUseCase,
) {
    suspend operator fun invoke(): List<MessageDetails> {
        val userId = fetchUserIdUseCase()
        val recentMessage =
            socialRepository.getRecentMassagesList(userId).messages?.map { it.toMessageDetails(userId) }
        return changeData(userId, recentMessage!!)
    }

    private fun changeData(
        userId: Int,
        messages: List<MessageDetails>?
    ): List<MessageDetails> {
        Log.i("MMMMMMMMM",messages.toString())
        return messages!!.filter {
            it.messageSender.userId != userId
        }
    }
}