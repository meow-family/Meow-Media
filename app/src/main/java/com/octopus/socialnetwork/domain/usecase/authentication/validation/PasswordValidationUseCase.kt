package com.octopus.socialnetwork.domain.usecase.authentication.validation

import com.octopus.socialnetwork.domain.enums.InputFieldValidation
import javax.inject.Inject

class PasswordValidationUseCase @Inject constructor() {
    operator fun invoke(password: String): InputFieldValidation {

        return if (password.isNotBlank()) {
            if (isShortPassword(password)) {
                InputFieldValidation.SHORT
            } else {
                if (isLongPassword(password)) {
                    InputFieldValidation.LONG
                } else {
                    if (isPasswordSymbolClean(password)) {
                        InputFieldValidation.VALID
                    } else {
                        InputFieldValidation.INVALID
                    }
                }
            }

        } else {
            InputFieldValidation.EMPTY
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