package com.octopus.socialnetwork.ui.util

import com.octopus.socialnetwork.R
import java.util.regex.Pattern


fun requiredValidation(text: String): Int? {
    return if (text.isEmpty() || text.isBlank()) R.string.is_required else null
}

fun passwordShortValidation(password: String): Int? {
    if (password.isEmpty() || password.isBlank()) {
        return R.string.is_required
    }
    return if (password.length < 6) R.string.password_short else null
}

fun emailValidation(email: String): Int? {
    if (email.isEmpty() || email.isBlank()) {
        return R.string.is_required
    }
    return if (Pattern.matches(
            Constants.REGEX_EMAIL_VALIDATION,
            email
        )
    ) null else R.string.invalid_email
}