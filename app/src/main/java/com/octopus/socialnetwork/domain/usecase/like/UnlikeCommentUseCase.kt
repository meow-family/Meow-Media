package com.octopus.socialnetwork.domain.usecase.like

import android.util.Log
import com.octopus.socialnetwork.data.repository.social.SocialRepository

import javax.inject.Inject

class UnlikeCommentUseCase @Inject constructor(
    private val socialRepository: SocialRepository
) {
    suspend operator fun invoke(userId: Int, commentId: Int, typeContent: String = "comment"): Int? {
        val response = socialRepository.unlike(userId, commentId, typeContent)
        Log.i("TESTING", response.toString())
        return response.count
    }
}