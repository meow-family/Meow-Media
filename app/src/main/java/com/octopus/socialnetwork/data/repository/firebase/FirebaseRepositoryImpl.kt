package com.octopus.socialnetwork.data.repository.firebase

import com.octopus.socialnetwork.data.remote.response.dto.user.UserFirebaseDTO
import com.octopus.socialnetwork.data.remote.service.FirebaseService
import com.octopus.socialnetwork.domain.mapper.user.toUserFirebase
import com.octopus.socialnetwork.domain.model.user.UserFirebase
import javax.inject.Inject

class FirebaseRepositoryImpl @Inject constructor(
    private val firebaseService: FirebaseService,
) : FirebaseRepository {

    override suspend fun createUser(user: UserFirebaseDTO) {
        firebaseService.createUser(user)
    }

    override suspend fun updateUser(user: UserFirebaseDTO) {
        firebaseService.updateUser(user)
    }

    override suspend fun updateUserToken(userId: String, token: String) {
        firebaseService.updateUserToken(userId, token)
    }

    override suspend fun getUserToken(userId: String): String? {
        TODO("Not yet implemented")
    }

    override suspend fun getUser(userId: String): UserFirebase? {
        return firebaseService.getUser(userId)?.toUserFirebase()
    }
}