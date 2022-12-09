package com.octopus.socialnetwork.domain.usecase.authentication

import com.octopus.socialnetwork.ui.screen.register.uistate.NameState
import javax.inject.Inject


class NameValidationUseCase @Inject constructor() {
    operator fun invoke(name: String): NameState {
        return if (name.isNotBlank()) {
            if (isNameSymbolClean(name)) {
                NameState.VALID
            } else {
                NameState.INVALID
            }
        } else {
            NameState.EMPTY
        }
    }


    private fun isNameSymbolClean(name: String): Boolean {
        return name.matches(NAME_REGEX)
    }

    companion object {
        val NAME_REGEX = Regex("^[A-Za-z][A-Za-z0-9]$")
    }

}