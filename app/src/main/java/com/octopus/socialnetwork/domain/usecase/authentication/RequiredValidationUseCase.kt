package com.octopus.socialnetwork.domain.usecase.authentication

import com.octopus.socialnetwork.ui.screen.register.uistate.RequiredState
import javax.inject.Inject

class RequiredValidationUseCase @Inject constructor() {
    operator fun invoke(value: String): RequiredState {
        return if (value.isNotBlank()) {
            RequiredState.VALID
        } else {
            RequiredState.EMPTY
        }
    }
}
