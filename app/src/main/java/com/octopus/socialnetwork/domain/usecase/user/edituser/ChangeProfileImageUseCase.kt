package com.octopus.socialnetwork.domain.usecase.user.edituser

import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.user.toUserDetails
import com.octopus.socialnetwork.domain.model.user.UserDetails
import com.octopus.socialnetwork.domain.usecase.authentication.FetchUserIdUseCase
import kotlinx.coroutines.flow.last
import java.io.File
import javax.inject.Inject

class ChangeProfileImageUseCase @Inject constructor(
    private val fetchUserIdUseCase: FetchUserIdUseCase,
    private val socialRepository: SocialRepository
) {
    suspend operator fun invoke(profileImage: File): UserDetails {
        val myUserId = fetchUserIdUseCase().last()
        return socialRepository.addProfilePicture(myUserId, profileImage).toUserDetails()
    }
}