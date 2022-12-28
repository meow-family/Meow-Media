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
    var showPassword: Boolean = false,
    val genderOption: List<String> = listOf("male", "female")
)

data class TextFieldUiState(
    var text: String = "",
    var error: Int? = R.string.is_required,
    var isValid: Boolean = false
)
