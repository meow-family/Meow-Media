package com.octopus.socialnetwork.ui.screen.register.uistate


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

enum class UserNameState(val message: String?) {
    EMPTY("is empty "),
    SHORT("is short "),
    LONG("is Long "),
    INVALID("Is not Clean "),
    VALID(null),
}

enum class EmailState(val message: String?) {
    EMPTY("is empty "),
    INVALID("Is not INVALID "),
    NOT_CONFIRM("not match email"),
    VALID(null),
}


enum class PasswordState(val message: String?) {
    EMPTY("is empty "),
    SHORT("is short "),
    LONG("is Long "),
    INVALID("Is not Clean "),
    VALID(null),
}

enum class NameState(val message: String?) {
    EMPTY("is empty "),
    INVALID("Is not Clean "),
    VALID(null),
}

enum class RequiredState(val message: String?) {
    EMPTY("is empty "),
    VALID(null),
}
