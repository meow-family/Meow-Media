package com.octopus.socialnetwork.data.repository.authentication_repository

import com.octopus.socialnetwork.data.remote.dto.auth.AuthResponse
import com.octopus.socialnetwork.data.remote.service.SocialService
import retrofit2.Response
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(
   private val service: SocialService,
): SignUpRepository {
    override suspend fun signup(
        firstName: String,
        lastName: String,
        email: String,
        reEmail: String,
        gender: String,
        birthDate: String,
        userName: String,
        password: String,
    ): Response<AuthResponse> {
        return service.signup(firstName, lastName, email, reEmail, gender, birthDate, userName, password)
    }
}