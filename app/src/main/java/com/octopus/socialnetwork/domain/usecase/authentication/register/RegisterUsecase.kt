package com.octopus.socialnetwork.domain.usecase.authentication.register

import android.util.Log
import com.octopus.socialnetwork.data.repository.authentication.AuthenticationRepository
import com.octopus.socialnetwork.domain.mapper.toParamRegisterDto
import com.octopus.socialnetwork.domain.mapper.user.toUserFirebaseDTO
import com.octopus.socialnetwork.domain.model.user.ParamRegister
import com.octopus.socialnetwork.domain.model.user.UserFirebase
import com.octopus.socialnetwork.domain.utils.Constants.REQUEST_SUCCEED
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val signInRepository: AuthenticationRepository,
    private val authenticationRepository: AuthenticationRepository
) {
    suspend operator fun invoke(params: ParamRegister): Boolean {

        val response = signInRepository.register(params.toParamRegisterDto())

        return if (response.code == REQUEST_SUCCEED) {
            val userResponse = response.result
            val userInfo = UserFirebase(
                userId = userResponse.userId ?: 0,
                fullName = userResponse.fullname ?: "",
                username = userResponse.username ?: "",
                token = ""
            ).toUserFirebaseDTO()
            authenticationRepository.createUser(userInfo)
            true
        } else {
            Log.v("MEOW", "error create account  ${response.code} ${response.message}")
            false
        }
    }

}
