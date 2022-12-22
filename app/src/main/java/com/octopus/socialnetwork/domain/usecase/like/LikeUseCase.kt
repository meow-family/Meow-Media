package com.octopus.socialnetwork.domain.usecase.like

import android.util.Log
import com.octopus.socialnetwork.data.repository.social.SocialRepository
import javax.inject.Inject

class LikeUseCase @Inject constructor(
    private val socialRepository: SocialRepository,
) {
    suspend operator fun invoke(userId: Int, contentId: Int, contentType: String): Int? {
        val response = socialRepository.like(userId, contentId, contentType)
        Log.i("TESTING","response.count : ${response.count}")
        return response.count
    }
}