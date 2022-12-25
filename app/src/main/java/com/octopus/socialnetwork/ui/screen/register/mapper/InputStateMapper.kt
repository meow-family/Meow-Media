package com.octopus.socialnetwork.ui.screen.register.mapper

import com.octopus.socialnetwork.domain.usecase.authentication.validation.InputFieldValidation
import com.octopus.socialnetwork.ui.screen.register.uistate.InputFieldState


fun InputFieldValidation.toInputFieldUiState(): InputFieldState {
    return when (this) {
        InputFieldValidation.EMPTY -> InputFieldState.EMPTY
        InputFieldValidation.INVALID -> InputFieldState.INVALID
        InputFieldValidation.VALID -> InputFieldState.VALID
        else -> InputFieldState.EMPTY
    }
}