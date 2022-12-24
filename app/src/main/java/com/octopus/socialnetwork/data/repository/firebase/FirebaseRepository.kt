package com.octopus.socialnetwork.data.repository.firebase

import com.octopus.socialnetwork.data.remote.response.dto.user.UserFirebaseDTO
import com.octopus.socialnetwork.domain.model.user.UserFirebase

interface FirebaseRepository {
    suspend fun createUser(user: UserFirebaseDTO)
    suspend fun updateUser(user: UserFirebaseDTO)
    suspend fun updateUserToken(userId: String, token: String)

    suspend fun getUser(userId: String): UserFirebase?
}