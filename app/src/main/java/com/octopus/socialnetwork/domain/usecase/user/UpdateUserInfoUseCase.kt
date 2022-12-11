package com.octopus.socialnetwork.domain.usecase.user

import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.user.toUserEdit
import com.octopus.socialnetwork.domain.model.user.UserEdit
import javax.inject.Inject


class UpdateUserInfoUseCase @Inject constructor(
    private val socialRepository: SocialRepository,
) {
    suspend operator fun invoke(currentUserId: Int, firstName: String, lastName: String, email: String, currentPassword: String, newPassword: String, newGender: String): UserEdit {
        return socialRepository.editUser(currentUserId, firstName, lastName, email, currentPassword, newPassword, newGender).toUserEdit()
    }
}