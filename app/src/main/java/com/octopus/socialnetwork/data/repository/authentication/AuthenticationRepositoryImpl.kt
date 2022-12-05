package com.octopus.socialnetwork.data.repository.authentication

import com.octopus.socialnetwork.data.remote.response.base.BaseResponse
import com.octopus.socialnetwork.data.remote.response.dto.auth.AuthResponse
import com.octopus.socialnetwork.data.remote.response.dto.auth.RegisterDto
import com.octopus.socialnetwork.data.remote.service.SocialService
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val service: SocialService,
) : AuthenticationRepository {

    override suspend fun login(username: String, password: String): BaseResponse<AuthResponse> {
        return service.login(username, password)
    }

    override suspend fun register(
        firstName: String,
        lastName: String,
        email: String,
        reEmail: String,
        gender: String,
        birthDate: String,
        userName: String,
        password: String,
    ): BaseResponse<RegisterDto> {
        return service.register(
            firstName, lastName, email, reEmail, gender, birthDate, userName, password
        )
    }
}