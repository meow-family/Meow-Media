package com.octopus.socialnetwork.ui.screen.register.mapper

import com.octopus.socialnetwork.domain.usecase.authentication.validation.InputFieldValidation
import com.octopus.socialnetwork.ui.screen.register.state.uistate.EmailState

fun InputFieldValidation.toEmailUiState(): EmailState {
    return when (this) {
        InputFieldValidation.EMPTY -> EmailState.EMPTY
        InputFieldValidation.INVALID -> EmailState.INVALID
        InputFieldValidation.NOT_CONFIRM -> EmailState.NOT_CONFIRM
        InputFieldValidation.VALID -> EmailState.VALID
        else -> EmailState.EMPTY
    }
}