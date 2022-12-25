package com.octopus.socialnetwork.data.repository.authentication

import com.octopus.socialnetwork.data.remote.response.base.BaseResponse
import com.octopus.socialnetwork.data.remote.response.dto.auth.AuthResponse
import com.octopus.socialnetwork.data.remote.response.dto.auth.RegisterDto
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

    fun getUserId(): Flow<Int?>

    suspend fun getLocalFcmToken(): String?
    suspend fun deleteUserId()
}