package com.octopus.socialnetwork.domain.usecase.authentication

import com.octopus.socialnetwork.domain.enums.InputFieldValidation
import javax.inject.Inject


class UserNameValidationUseCase @Inject constructor() {
    operator fun invoke(username: String): InputFieldValidation {
        return if (username.isNotBlank()) {
            if (isShortUserName(username)) {
                InputFieldValidation.SHORT
            } else {
                if (isLongUserName(username)) {
                    InputFieldValidation.LONG
                } else {
                    if (username.matches(USERNAME_REGEX)) {
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


    private fun isShortUserName(username: String): Boolean = username.length < 6
    private fun isLongUserName(username: String): Boolean = username.length > 29

    companion object {
        val USERNAME_REGEX = Regex("^[A-Za-z][A-Za-z0-9_]{5,29}$")
    }

}