package com.octopus.socialnetwork.data.remote.firebase

import com.octopus.socialnetwork.data.remote.response.dto.user.UserFirebaseDTO
import com.octopus.socialnetwork.domain.mapper.user.toUserFirebase
import com.octopus.socialnetwork.domain.model.user.UserFirebase
import javax.inject.Inject

class FirebaseRepositoryImpl @Inject constructor(
    private val firestoreService: FirestoreService,
) : FirebaseRepository {

    override suspend fun createUser(user: UserFirebaseDTO) {
        firestoreService.createUser(user)
    }

    override suspend fun updateUser(user: UserFirebaseDTO) {
        firestoreService.updateUser(user)
    }

    override suspend fun updateUserToken(userId: String, token: String) {
        firestoreService.updateUserToken(userId, token)
    }

    override suspend fun getUser(userId: String): UserFirebase? {
        return firestoreService.getUser(userId)?.toUserFirebase()
    }
}