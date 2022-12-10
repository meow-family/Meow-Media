package com.octopus.socialnetwork.domain.usecase.like

import android.util.Log
import com.octopus.socialnetwork.data.repository.social.SocialRepository

import javax.inject.Inject

class UnlikeUseCase @Inject constructor(
    private val socialRepository: SocialRepository
) {
    suspend operator fun invoke(userId: Int, postId: Int, typeContent: String = "post"): Int? {
        val response = socialRepository.unlike(userId, postId, typeContent)
        Log.i("TESTING", response.toString())
        return response.count
    }
}