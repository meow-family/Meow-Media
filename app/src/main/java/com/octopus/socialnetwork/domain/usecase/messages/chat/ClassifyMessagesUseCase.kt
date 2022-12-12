package com.octopus.socialnetwork.domain.usecase.messages.chat

import com.octopus.socialnetwork.domain.model.messages.MessageDetails
import com.octopus.socialnetwork.domain.usecase.user.FetchUserIdUseCase
import javax.inject.Inject

class ClassifyMessagesUseCase @Inject constructor(
    private val fetchUserIdUseCase: FetchUserIdUseCase,
){
    operator fun invoke(messages: List<MessageDetails>) : List<MessageDetails> {
        val userId = fetchUserIdUseCase()
        return messages.map {
            if (userId == it.userId) {
                it.copy(isSentByMe = true)
            } else {
                it.copy(isSentByMe = false)
            }
        }.sortedBy { it.time }
    }
}
