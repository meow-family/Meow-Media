package com.octopus.socialnetwork.domain.usecase.authentication

import com.octopus.socialnetwork.ui.screen.register.uistate.UserNameState
import javax.inject.Inject


class UserNameValidationUseCase @Inject constructor() {
    operator fun invoke(username: String): UserNameState {
        return if (username.isNotBlank()) {
            if (isShortUserName(username)) {
                UserNameState.SHORT
            } else {
                if (isLongUserName(username)) {
                    UserNameState.LONG
                } else {
                    if (username.matches(USERNAME_REGEX)) {
                        UserNameState.VALID
                    } else {
                        UserNameState.INVALID
                    }
                }
            }
        } else {
            UserNameState.EMPTY

        }
    }


    private fun isShortUserName(username: String): Boolean = username.length < 6
    private fun isLongUserName(username: String): Boolean = username.length > 29

    companion object {
        val USERNAME_REGEX = Regex("^[A-Za-z][A-Za-z0-9_]{5,29}$")
    }

}