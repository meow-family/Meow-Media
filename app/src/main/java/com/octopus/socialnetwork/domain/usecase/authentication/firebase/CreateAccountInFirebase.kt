package com.octopus.socialnetwork.domain.usecase.authentication.firebase

import com.octopus.socialnetwork.data.remote.response.dto.user.UserFirebaseDTO
import com.octopus.socialnetwork.data.remote.firebase.FirebaseRepository
import javax.inject.Inject

class CreateAccountInFirebase @Inject constructor(
    val firebase: FirebaseRepository,
) {
    suspend operator fun invoke(user: UserFirebaseDTO) {
        return firebase.createUser(user)
    }
}