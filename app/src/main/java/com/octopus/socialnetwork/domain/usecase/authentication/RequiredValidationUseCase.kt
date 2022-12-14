package com.octopus.socialnetwork.domain.usecase.authentication

import com.octopus.socialnetwork.domain.enums.InputFieldValidation
import javax.inject.Inject

class RequiredValidationUseCase @Inject constructor() {
    operator fun invoke(value: String): InputFieldValidation {
        return if (value.isNotBlank()) {
            InputFieldValidation.VALID
        } else {
            InputFieldValidation.EMPTY
        }
    }
}
