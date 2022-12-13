package com.octopus.socialnetwork.domain.usecase.user

import android.net.Uri
import com.octopus.socialnetwork.data.remote.response.dto.photo.UserProfileDto
import com.octopus.socialnetwork.data.repository.social.SocialRepository
import java.io.File
import java.net.URI
import javax.inject.Inject


class ChangeProfileImageUseCase @Inject constructor(
    private val socialRepository: SocialRepository,
) {
    suspend operator fun invoke(file: String, userId: Int): UserProfileDto {
        return socialRepository.changeProfileImage(file, userId)
    }
}