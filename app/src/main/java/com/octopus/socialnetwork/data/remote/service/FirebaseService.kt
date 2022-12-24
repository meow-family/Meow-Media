package com.octopus.socialnetwork.data.remote.service

import com.octopus.socialnetwork.data.remote.response.dto.user.UserFirebaseDTO

interface FirebaseService {
    suspend fun createUser(user: UserFirebaseDTO)
    suspend fun updateUser(user: UserFirebaseDTO)
    suspend fun updateUserToken(userId: String, token: String)
    suspend fun getUser(userId: String): UserFirebaseDTO?
}