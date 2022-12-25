package com.octopus.socialnetwork.data.repository.authentication

import com.octopus.socialnetwork.data.remote.response.base.BaseResponse
import com.octopus.socialnetwork.data.remote.response.dto.auth.AuthResponse
import com.octopus.socialnetwork.data.remote.response.dto.auth.RegisterDto
import com.octopus.socialnetwork.data.remote.response.dto.user.UserFirebaseDTO
import com.octopus.socialnetwork.domain.model.user.UserFirebase
import kotlinx.coroutines.flow.Flow

interface AuthenticationRepository {
    suspend fun login(username: String, password: String): AuthResponse

    suspend fun register(
        firstName: String,
        lastName: String,
        email: String,
        reEmail: String,
        gender: String,
        birthDate: String,
        userName: String,
        password: String
    ): BaseResponse<RegisterDto>

    suspend fun deleteUserId()
    suspend fun getUserId(): Int?

    suspend fun getLocalFcmToken(): String?
    suspend fun writeFcmToken(token:String)

    suspend fun createUser(user: UserFirebaseDTO)
    suspend fun updateUser(user: UserFirebaseDTO)
    suspend fun updateUserToken(userId: String, token: String)
    suspend fun getFirebaseUser(userId: String): UserFirebaseDTO?

}