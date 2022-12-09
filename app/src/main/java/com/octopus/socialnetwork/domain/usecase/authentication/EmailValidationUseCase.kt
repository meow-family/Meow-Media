package com.octopus.socialnetwork.domain.usecase.authentication

import androidx.core.util.PatternsCompat
import com.octopus.socialnetwork.ui.screen.register.uistate.EmailState
import javax.inject.Inject

class EmailValidationUseCase @Inject constructor() {
    operator fun invoke(email: String): EmailState {
        return if (email.isNotBlank()) {
            if (isEmail(email)) {
                EmailState.VALID
            } else {
                EmailState.INVALID
            }

        } else {
            EmailState.EMPTY
        }

    }


    fun confirmEmail(email: String, reEmail: String): EmailState {
        val emailValidation = invoke(email)
        return if (emailValidation == EmailState.VALID) {
            if (isEmailConform(email, reEmail)) {
                EmailState.VALID
            } else {
                EmailState.NOT_CONFIRM
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