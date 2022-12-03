package com.octopus.socialnetwork.domain.usecase.user

import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.user.asUserEdit
import com.octopus.socialnetwork.domain.mapper.user.asUserPosts
import com.octopus.socialnetwork.domain.model.user.UserEdit
import com.octopus.socialnetwork.domain.model.user.UserPosts
import javax.inject.Inject


class FetchUserEditUseCase @Inject constructor(
    private val socialRepository: SocialRepository,
) {
    suspend operator fun invoke(
        currentUserId: Int,
        firstName: String,
        lastName: String, email: String,
        currentPassword: String,
        newPassword: String
    ): UserEdit {
        return socialRepository.editUser(
            currentUserId,
            firstName,
            lastName,
            email,
            currentPassword,
            newPassword
        ).asUserEdit()
    }
}