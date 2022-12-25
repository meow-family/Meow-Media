package com.octopus.socialnetwork.ui.screen.register.mapper

import com.octopus.socialnetwork.domain.usecase.authentication.validation.InputFieldValidation
import com.octopus.socialnetwork.ui.screen.register.uistate.PasswordState

fun InputFieldValidation.toPasswordUiState(): PasswordState {
    return when (this) {
        InputFieldValidation.EMPTY -> PasswordState.EMPTY
        InputFieldValidation.SHORT -> PasswordState.SHORT
        InputFieldValidation.LONG -> PasswordState.LONG
        InputFieldValidation.INVALID -> PasswordState.INVALID
        InputFieldValidation.VALID -> PasswordState.VALID
        else -> PasswordState.EMPTY
    }
}