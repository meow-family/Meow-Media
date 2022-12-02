package com.octopus.socialnetwork.domain.usecase.massages

import com.octopus.socialnetwork.data.repository.messaging.MessagingRepository
import javax.inject.Inject

class MessageListUseCase @Inject constructor(
    private val socialRepository: MessagingRepository
) {
//    suspend operator fun invoke(from: Int, to: Int, message: String): MessageDetails {
//        return socialRepository.messageList(from, to)
//    }
}