package com.octopus.socialnetwork.domain.usecase.authentication.validation

import com.octopus.socialnetwork.domain.enums.InputFieldValidation
import javax.inject.Inject


class NameValidationUseCase @Inject constructor() {
    operator fun invoke(name: String): InputFieldValidation {
        return if (name.isNotBlank()) {
            if (isNameSymbolClean(name)) {
                InputFieldValidation.VALID
            } else {
                InputFieldValidation.INVALID
            }
        } else {
            InputFieldValidation.EMPTY
        }
    }


    private fun isNameSymbolClean(name: String): Boolean {
        return name.matches(NAME_REGEX)
    }

    companion object {
        val NAME_REGEX = Regex("^[A-Za-z][A-Za-z0-9_]{0,50}\$")
    }

}