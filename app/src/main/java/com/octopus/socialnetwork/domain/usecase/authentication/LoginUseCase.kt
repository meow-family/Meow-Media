package com.octopus.socialnetwork.domain.usecase.authentication

import com.octopus.socialnetwork.domain.usecase.authentication.validation_use_case.ValidateUseCase
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val validateUseCase: ValidateUseCase
){
    suspend operator fun invoke(username: String, password: String): LoginResponse {
        return validateUseCase.invoke(username, password)
    }
}