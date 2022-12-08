package com.octopus.socialnetwork.domain.usecase.authentication

import javax.inject.Inject

class UserNameValidationUseCase @Inject constructor() {
    operator fun invoke(username: String): Boolean {
        return username.matches(USERNAME_REGEX)
    }

    companion object {
        val USERNAME_REGEX = Regex("^[A-Za-z][A-Za-z0-9_]{7,29}$")
    }
}