package com.octopus.socialnetwork.domain.usecase.like

import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.usecase.authentication.FetchUserIdUseCase
import com.octopus.socialnetwork.domain.utils.SoundPlayer
import javax.inject.Inject

class ToggleLikeUseCase @Inject constructor(
    private val likeUseCase: LikeUseCase,
    private val unlikeUseCase: UnlikeUseCase,
    private val fetchUserIdUseCase: FetchUserIdUseCase,
    private val socialRepository: SocialRepository,
    private val soundPlayer: SoundPlayer
) {
    suspend operator fun invoke(
        contentId: Int,
        totalLikes: Int,
        isLiked: Boolean,
        contentType: String
    ): Int? {
        val userId = fetchUserIdUseCase()
        return if (isLiked) {
            // LIKED -> UNLIKE
            val newLikesCount = try {
                val newLikesCount = unlikeUseCase(userId, contentId, contentType)
                socialRepository.updatePostLikeStatusLocally(contentId, false, totalLikes.minus(1))
                newLikesCount
            } catch (e: Exception) {
                socialRepository.updatePostLikeStatusLocally(contentId, true, totalLikes)
                totalLikes
            }
            newLikesCount
        } else {
            val newLikesCount = try {
                socialRepository.updatePostLikeStatusLocally(contentId, true, totalLikes.plus(1))
                val newLikesCount = likeUseCase(userId, contentId, contentType)
                soundPlayer.playSound()
                newLikesCount
            } catch (e: Exception) {
                socialRepository.updatePostLikeStatusLocally(contentId, false, totalLikes)
                totalLikes
            }
            newLikesCount
            // UNLIKED -> LIKE
        }
    }
}