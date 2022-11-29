package com.octopus.socialnetwork.data.repository.authentication_repository

import com.octopus.socialnetwork.data.remote.dto.auth.AuthResponse
import com.octopus.socialnetwork.data.remote.service.SocialService
import retrofit2.Response
import javax.inject.Inject

class SignInRepositoryImpl @Inject constructor(
    private val service: SocialService,
): SignInRepository {
    override suspend fun login(username: String, password: String): Response<AuthResponse> {
        return service.login(username, password)
    }
}