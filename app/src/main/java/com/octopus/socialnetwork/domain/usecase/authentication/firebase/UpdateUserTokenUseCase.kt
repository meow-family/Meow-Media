package com.octopus.socialnetwork.domain.usecase.authentication.firebase

import com.octopus.socialnetwork.data.remote.firebase.FirebaseRepository
import javax.inject.Inject

class UpdateUserTokenUseCase @Inject constructor(
    val firebase: FirebaseRepository,
) {
    suspend operator fun invoke(userId: String, token: String) {
        return firebase.updateUserToken(userId, token)
    }
}