package com.octopus.socialnetwork.data.repository.messaging

import com.octopus.socialnetwork.data.remote.service.SocialService
import javax.inject.Inject

class MessagingRepositoryImpl @Inject constructor(
    private val service: SocialService,
) : MessagingRepository {
}

