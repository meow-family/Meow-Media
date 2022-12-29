package com.octopus.socialnetwork.ui.screen.register.state.uistate

import com.octopus.socialnetwork.R


enum class UserNameState(val message: Int?) {
    EMPTY(R.string.username_empty),
    SHORT(R.string.username_short),
    LONG(R.string.username_long),
    INVALID(R.string.username_not_clean),
    VALID(null),
}

enum class EmailState(val message: Int?) {
    EMPTY(R.string.email_empty),
    INVALID(R.string.email_invalid),
    NOT_CONFIRM(R.string.email_not_confirm),
    VALID(null),
}


enum class PasswordState(val message: Int?) {
    EMPTY(R.string.password_empty),
    SHORT(R.string.password_short),
    LONG(R.string.password_long),
    INVALID(R.string.password_not_clean),
    VALID(null),
}

enum class InputFieldState(val message: Int?) {
    EMPTY(R.string.is_required),
    INVALID(R.string.invalid_name),
    VALID(null),
}
