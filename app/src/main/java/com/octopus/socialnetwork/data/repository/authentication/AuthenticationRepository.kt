package com.octopus.socialnetwork.data.repository.authentication

import com.octopus.socialnetwork.data.remote.response.base.BaseResponse
import com.octopus.socialnetwork.data.remote.response.dto.auth.LoginDto
import com.octopus.socialnetwork.data.remote.response.dto.auth.RegisterDto
import com.octopus.socialnetwork.data.remote.response.dto.user.UserFirebaseDTO
import kotlinx.coroutines.flow.Flow

interface AuthenticationRepository {
    suspend fun login(username: String, password: String): LoginDto

    suspend fun register(
        paramRegister: ParamRegisterDto
    ): BaseResponse<RegisterDto>

    suspend fun deleteUserId()
    fun getUserId(): Flow<Int?>

    suspend fun getLocalFcmToken(): String?
    suspend fun createUser(user: UserFirebaseDTO)

    suspend fun updateUser(user: UserFirebaseDTO)
    suspend fun updateUserToken(userId: String, token: String)
    suspend fun getFirebaseUser(userId: String): UserFirebaseDTO?

    suspend fun getFirebaseFcmToken(): String?
    suspend fun writeFcmToken(token: String)
}

data class ParamRegisterDto(
    val firstName: String,
    val lastName: String,
    val email: String,
    val reEmail: String,
    val gender: String,
    val birthDate: String,
    val userName: String,
    val password: String
)



