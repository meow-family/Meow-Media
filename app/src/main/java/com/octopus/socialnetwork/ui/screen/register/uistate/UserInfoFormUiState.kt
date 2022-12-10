package com.octopus.socialnetwork.ui.screen.register.uistate

import com.octopus.socialnetwork.R


data class UserInfoFormUiState(
    var userName: TextFieldUiState = TextFieldUiState(),
    var email: TextFieldUiState = TextFieldUiState(),
    var reEmail: TextFieldUiState = TextFieldUiState(),
    var password: TextFieldUiState = TextFieldUiState(),
    var firstName: TextFieldUiState = TextFieldUiState(),
    var lastName: TextFieldUiState = TextFieldUiState(),
    var gender: TextFieldUiState = TextFieldUiState(),
    var birthDate: TextFieldUiState = TextFieldUiState(),
)

enum class InputState

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

enum class NameState(val message: Int?) {
    EMPTY(R.string.is_required),
    INVALID(R.string.invalid_name),
    VALID(null),
}

enum class RequiredState(val message: Int?) {
    EMPTY(R.string.is_required),
    VALID(null),
}
