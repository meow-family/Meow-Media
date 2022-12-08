package com.octopus.socialnetwork.domain.usecase.authentication

import androidx.core.util.PatternsCompat
import javax.inject.Inject

class EmailValidationUseCase @Inject constructor() {
    operator fun invoke(email: String): Boolean {
        return PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
    }
}