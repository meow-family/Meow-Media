package com.octopus.socialnetwork.domain.usecase.authentication.firebase

import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.user.toUser
import com.octopus.socialnetwork.domain.model.user.User
import javax.inject.Inject


class UpdateUserInfoUseCase @Inject constructor(
    private val socialRepository: SocialRepository,
) {
    suspend operator fun invoke(myUserId: Int, firstName: String, lastName: String, email: String, currentPassword: String, newPassword: String): User {
        return socialRepository.editUser(myUserId, firstName, lastName, email, currentPassword, newPassword).toUser()
    }
}