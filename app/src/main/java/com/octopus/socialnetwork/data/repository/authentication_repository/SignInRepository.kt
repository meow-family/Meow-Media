package com.octopus.socialnetwork.data.repository.authentication_repository

import com.octopus.socialnetwork.data.remote.dto.auth.AuthResponse
import retrofit2.Response

interface SignInRepository {
    suspend fun login(username: String, password: String): Response<AuthResponse>
}