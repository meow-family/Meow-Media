package com.octopus.socialnetwork.data.repository.authentication

import com.octopus.socialnetwork.SocialNetworkApplication.Companion.USER_ID_KEY
import com.octopus.socialnetwork.data.local.datastore.DataStorePreferences
import com.octopus.socialnetwork.data.remote.response.base.BaseResponse
import com.octopus.socialnetwork.data.remote.response.dto.auth.AuthResponse
import com.octopus.socialnetwork.data.remote.response.dto.auth.RegisterDto
import com.octopus.socialnetwork.data.remote.service.SocialService
import com.octopus.socialnetwork.domain.usecase.authentication.RegisterUseCase
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val service: SocialService,
    private val dataStorePreferences: DataStorePreferences,
) : AuthenticationRepository {

    override suspend fun login(username: String, password: String): AuthResponse {
        val response = service.login(username, password)
        if (response.code == REQUEST_SUCCEED) {
            response.result.id?.let {
                dataStorePreferences.writeString(USER_ID_KEY, it)
            }
        }
        return response.result
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

    override fun getUserId(): Int? {
        return dataStorePreferences.readString("user_id")
    }

    companion object {
        const val REQUEST_SUCCEED = "100"
    }
}