package com.octopus.socialnetwork.domain.usecase.authentication.firebase

import com.octopus.socialnetwork.data.remote.response.dto.user.UserFirebaseDTO
import com.octopus.socialnetwork.data.repository.authentication.AuthenticationRepository
import javax.inject.Inject

class CreateAccountInFirebase @Inject constructor(
    val authenticationRepository: AuthenticationRepository
) {
    suspend operator fun invoke(user: UserFirebaseDTO) {
        return authenticationRepository.createUser(user)
    }
}