package com.octopus.socialnetwork.domain.usecase.user

import com.octopus.socialnetwork.data.repository.firebase.FirebaseRepository
import javax.inject.Inject

class UpdateUserToken @Inject constructor(
    val firebase: FirebaseRepository,
) {
    suspend operator fun invoke(userId: String, token: String) {
        return firebase.updateUserToken(userId, token)
    }
}