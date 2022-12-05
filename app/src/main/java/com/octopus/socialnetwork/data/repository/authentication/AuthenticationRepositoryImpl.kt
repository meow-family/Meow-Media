package com.octopus.socialnetwork.data.repository.authentication

import com.octopus.socialnetwork.data.remote.response.base.BaseResponse
import com.octopus.socialnetwork.data.remote.response.dto.auth.AuthResponse
import com.octopus.socialnetwork.data.remote.response.dto.auth.RegisterDto
import com.octopus.socialnetwork.data.remote.service.SocialService
import com.octopus.socialnetwork.domain.usecase.authentication.RegisterUseCase
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val service: SocialService,
) : AuthenticationRepository {
    override suspend fun login(username: String, password: String): BaseResponse<AuthResponse> {
        return service.login(username, password)
    }

    override suspend fun register(params: RegisterUseCase.Params): BaseResponse<RegisterDto> {
        return service.register(
            firstName = params.firstName,
            lastName = params.lastName,
            email = params.email,
            reEmail = params.reEmail,
            gender = params.gender,
            birthDate = params.birthDate,
            userName = params.userName,
            password = params.password
        )
    }
}