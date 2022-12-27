package com.octopus.socialnetwork.domain.usecase.like

import android.util.Log
import com.octopus.socialnetwork.data.repository.social.SocialRepository

import javax.inject.Inject

class UnlikeUseCase @Inject constructor(
    private val socialRepository: SocialRepository
) {
    suspend operator fun invoke(userId: Int, postId: Int, contentType: String): Int? {
        val response = socialRepository.unlike(userId, postId, contentType)
        Log.i("MEOW","response.count : ${response.count}")
        return response.count
    }
}