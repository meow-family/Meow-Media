package com.octopus.socialnetwork.domain.usecase.authentication.firebase

import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.user.toUserDetails
import com.octopus.socialnetwork.domain.model.user.UserDetails
import javax.inject.Inject


class UpdateUserInfoUseCase @Inject constructor(
    private val socialRepository: SocialRepository,
) {
    suspend operator fun invoke(myUserId: Int, firstName: String, lastName: String, email: String, currentPassword: String, newPassword: String): UserDetails {
        return socialRepository.editUser(myUserId, firstName, lastName, email, currentPassword, newPassword).toUserDetails()
    }
}