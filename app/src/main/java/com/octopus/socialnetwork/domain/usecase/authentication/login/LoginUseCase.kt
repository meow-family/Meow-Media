package com.octopus.socialnetwork.domain.usecase.authentication.login

import android.util.Log
import com.octopus.socialnetwork.data.remote.response.dto.auth.LoginDto
import com.octopus.socialnetwork.data.repository.authentication.AuthenticationRepository
import kotlinx.coroutines.flow.last
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
) {
    suspend operator fun invoke(username: String, password: String): LoginDto {
        val user = authenticationRepository.login(username, password)
        if (user.id != null) {
            Log.i("USERID","user id is ${user.id}")
            authenticationRepository.getLocalFcmToken()?.let {
                Log.i("FCM_TOKEN","stored fcm token locally is $it")
                authenticationRepository.updateUserToken(
                    user.id.toString(),
                    it
                )
                authenticationRepository.storeLoginState(true)
                Log.i("FCM_TOKEN","stored fcm token in firestore")
            }
        }
        return user
    }
}