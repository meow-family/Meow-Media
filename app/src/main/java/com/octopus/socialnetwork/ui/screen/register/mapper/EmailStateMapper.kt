package com.octopus.socialnetwork.ui.screen.register.mapper

import com.octopus.socialnetwork.domain.utils.EmailValidation
import com.octopus.socialnetwork.ui.screen.register.uistate.EmailState

fun EmailValidation.toEmailUiState(): EmailState {
    return when (this) {
        EmailValidation.EMPTY -> EmailState.EMPTY
        EmailValidation.INVALID -> EmailState.INVALID
        EmailValidation.VALID -> EmailState.VALID
        else -> {
            EmailState.NOT_CONFIRM
        }
    }
}