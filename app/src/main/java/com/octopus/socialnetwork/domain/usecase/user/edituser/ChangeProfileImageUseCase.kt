package com.octopus.socialnetwork.domain.usecase.user.edituser

import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.user.toUser
import com.octopus.socialnetwork.domain.model.user.User
import com.octopus.socialnetwork.domain.usecase.authentication.FetchUserIdUseCase
import java.io.File
import javax.inject.Inject

class ChangeProfileImageUseCase @Inject constructor(
    private val fetchUserIdUseCase: FetchUserIdUseCase,
    private val socialRepository: SocialRepository
) {
    suspend operator fun invoke(profileImage: File): User {
        val myUserId = fetchUserIdUseCase()
        return socialRepository.addProfilePicture(myUserId, profileImage).toUser()
    }
}