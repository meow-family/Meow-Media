package com.octopus.socialnetwork.domain.usecase.user

import com.octopus.socialnetwork.data.repository.firebase.FirebaseRepository
import javax.inject.Inject


class GetUserToken @Inject constructor(
    private val firebase: FirebaseRepository,
) {
    suspend operator fun invoke(userId: String): String? {
        return firebase.getUser(userId)?.token
    }
}