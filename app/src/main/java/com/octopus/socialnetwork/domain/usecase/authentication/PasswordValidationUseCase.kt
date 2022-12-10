package com.octopus.socialnetwork.domain.usecase.authentication

import com.octopus.socialnetwork.ui.screen.register.uistate.PasswordState
import javax.inject.Inject

class PasswordValidationUseCase @Inject constructor() {
    operator fun invoke(password: String): PasswordState {

        return if (password.isNotBlank()) {
            if (isShortPassword(password)) {
                PasswordState.SHORT
            } else {
                if (isLongPassword(password)) {
                    PasswordState.LONG
                } else {
                    if (isPasswordSymbolClean(password)) {
                        PasswordState.VALID
                    } else {
                        PasswordState.INVALID
                    }
                }
            }

        } else {
            PasswordState.EMPTY
        }
    }

    private fun isShortPassword(password: String): Boolean {
        return password.length < 8
    }

    private fun isLongPassword(password: String): Boolean {
        return password.length > 50
    }

    private fun isPasswordSymbolClean(password: String): Boolean {
        return password.matches(PASSWORD_REGEX)
    }


    companion object {
        val PASSWORD_REGEX = Regex("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$")
    }

}