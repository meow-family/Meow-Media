package com.octopus.socialnetwork.domain.usecase.user

import com.octopus.socialnetwork.data.remote.response.dto.photo.UserProfileDto
import com.octopus.socialnetwork.data.repository.social.SocialRepository
import javax.inject.Inject


class ChangeProfileImageUseCase @Inject constructor(
    private val socialRepository: SocialRepository,
) {
    suspend operator fun invoke(filePath: String, userId: Int): UserProfileDto {
        return socialRepository.changeProfileImage(filePath, userId)
    }
}