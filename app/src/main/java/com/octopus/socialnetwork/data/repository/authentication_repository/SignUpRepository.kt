package com.octopus.socialnetwork.data.repository.authentication_repository

import com.octopus.socialnetwork.data.remote.dto.auth.AuthResponse
import retrofit2.Response

interface SignUpRepository {
    suspend fun signup(firstName: String,
                       lastName: String,
                       email: String,
                       reEmail: String,
                       gender: String,
                       birthDate: String,
                       userName: String,
                       password: String,
    ): Response<AuthResponse>
}