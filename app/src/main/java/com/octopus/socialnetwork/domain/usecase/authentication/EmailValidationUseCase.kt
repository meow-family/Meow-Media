package com.octopus.socialnetwork.domain.usecase.authentication

import androidx.core.util.PatternsCompat
import com.octopus.socialnetwork.domain.utils.EmailValidation
import javax.inject.Inject

class EmailValidationUseCase @Inject constructor() {
    operator fun invoke(email: String): EmailValidation {
        return if (email.isNotBlank()) {
            if (isEmail(email)) {
                EmailValidation.VALID
            } else {
                EmailValidation.INVALID
            }

        } else {
            EmailValidation.EMPTY
        }
    }

    fun confirmEmail(email: String, reEmail: String): EmailValidation {
        val emailValidation = invoke(email)
        return if (emailValidation == EmailValidation.VALID) {
            if (isEmailConform(email, reEmail)) {
                EmailValidation.VALID
            } else {
                EmailValidation.NOT_CONFIRM
            }
        } else {
            return emailValidation
        }
    }

    private fun isEmail(email: String): Boolean {
        return PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isEmailConform(email: String, reEmail: String): Boolean {
        return email == reEmail
    }
}