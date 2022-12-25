package com.octopus.socialnetwork.data.repository.authentication

import com.octopus.socialnetwork.SocialNetworkApplication.Companion.USER_ID_KEY
import com.octopus.socialnetwork.data.local.datastore.DataStorePreferences
import com.octopus.socialnetwork.data.remote.response.base.BaseResponse
import com.octopus.socialnetwork.data.remote.response.dto.auth.AuthResponse
import com.octopus.socialnetwork.data.remote.response.dto.auth.RegisterDto
import com.octopus.socialnetwork.data.remote.service.SocialService
import com.octopus.socialnetwork.domain.usecase.authentication.register.RegisterUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val service: SocialService,
    private val dataStorePreferences: DataStorePreferences,
) : AuthenticationRepository {

    override suspend fun login(username: String, password: String): AuthResponse {
        val response = service.login(username, password)
        if (response.code == REQUEST_SUCCEED) {
            dataStorePreferences.writeInt(USER_ID_KEY, response.result.id ?: 0)
        }
        return response.result
    }


    override suspend fun register(
        firstName: String,
        lastName: String,
        email: String,
        reEmail:String,
        gender: String,
        birthDate: String,
        userName: String,
        password: String
    ): BaseResponse<RegisterDto> {
        return service.register(
            firstName = firstName,
            lastName = lastName,
            email = email,
            reEmail = reEmail,
            gender = gender,
            birthDate = birthDate,
            userName = userName,
            password = password
        )
    }

    override fun getUserId(): Flow<Int?> {
        return dataStorePreferences.readInt("user_id")
    }

    override suspend fun getLocalFcmToken(): String? {
        return dataStorePreferences.readString("FCM_TOKEN")
    }

    override suspend fun deleteUserId() {
        dataStorePreferences.writeInt("user_id", NO_SUCH_ID)
    }

    companion object {
        const val REQUEST_SUCCEED = "100"
        const val NO_SUCH_ID = -1
    }
}