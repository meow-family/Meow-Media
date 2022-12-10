package com.octopus.socialnetwork.domain.usecase.authentication.validation_use_case

import android.util.Log
import javax.inject.Inject

class ValidateUseCase @Inject constructor() {
    operator fun invoke(username: String, password: String): Boolean {
        return (isValidUsername(username) && isValidPassword(password))
    }

    private fun isValidUsername(username: String): Boolean {
        Log.i(
            "TESTING",
            "entered username or email: $username is ${
                username.matches(USERNAME_REGEX) || username.matches(
                    EMAIL_REGEX
                )
            }"
        )
        return username.matches(USERNAME_REGEX) || username.matches(EMAIL_REGEX)
    }

    private fun isValidPassword(password: String): Boolean {
        Log.i(
            "TESTING",
            "entered password: $password is ${password.matches(PASSWORD_REGEX)}"
        )
        return password.matches(PASSWORD_REGEX)
    }


    companion object {
        val USERNAME_REGEX = Regex("^[A-Za-z][A-Za-z0-9_]{7,29}$")
        val EMAIL_REGEX = Regex("^([a-z0-9_.-]+)@([\\da-z.-]+)\\.([a-z.]{2,6})\$")
        val PASSWORD_REGEX = Regex("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$")
        //PASSWORD REGEX: Minimum eight characters,at least one uppercase letter, one lowercase, one number
    }

}
