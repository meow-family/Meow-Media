package com.octopus.socialnetwork.domain.usecase.authentication.validation

import androidx.core.util.PatternsCompat
import javax.inject.Inject

class EmailValidationUseCase @Inject constructor() {
    operator fun invoke(email: String): InputFieldValidation {
        return if (email.isNotBlank()) {
            if (isEmail(email)) {
                InputFieldValidation.VALID
            } else {
                InputFieldValidation.INVALID
            }

        } else {
            InputFieldValidation.EMPTY
        }
    }

    fun confirmEmail(email: String, reEmail: String): InputFieldValidation {
        val emailValidation = invoke(email)
        return if (emailValidation == InputFieldValidation.VALID) {
            if (isEmailConform(email, reEmail)) {
                InputFieldValidation.VALID
            } else {
                InputFieldValidation.NOT_CONFIRM
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