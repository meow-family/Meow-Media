package com.octopus.socialnetwork.data.repository.authentication

import com.octopus.socialnetwork.SocialNetworkApplication.Companion.USER_ID_KEY
import com.octopus.socialnetwork.data.local.datastore.DataStorePreferences
import com.octopus.socialnetwork.data.remote.response.base.BaseResponse
import com.octopus.socialnetwork.data.remote.response.dto.auth.AuthResponse
import com.octopus.socialnetwork.data.remote.service.SocialService
import com.octopus.socialnetwork.data.util.Constants.REQUEST_SUCCEED
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val service: SocialService,
    private val dataStorePreferences: DataStorePreferences,
) :AuthenticationRepository {

    override suspend fun login(username: String, password: String): AuthResponse {
        val response = service.login(username, password)
        if (response.code == REQUEST_SUCCEED){
            response.result.id?.let { saveUserId(it) }
        }
        return response.result
    }
    private suspend fun saveUserId(userId: Int) {
        dataStorePreferences.writeString(USER_ID_KEY, userId)
    }
  

    override suspend fun signup(
        firstName: String,
        lastName: String,
        email: String,
        reEmail: String,
        gender: String,
        birthDate: String,
        userName: String,
        password: String,
    ): BaseResponse<AuthResponse> {
        return service.signup(
                firstName,
                lastName,
                email,
                reEmail,
                gender,
                birthDate,
                userName,
                password
            )

    }
}