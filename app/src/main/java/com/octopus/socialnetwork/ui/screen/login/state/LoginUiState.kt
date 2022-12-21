package com.octopus.socialnetwork.ui.screen.login.state

import com.octopus.socialnetwork.ui.screen.register.uistate.TextFieldUiState

data class LoginUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String = "",
    var showPassword: Boolean = false,
    val isValidInputs: Boolean = false,
    val isDisplayErrorValidationInputs: Boolean = false,
    val userInput: UserInputLoginUiState = UserInputLoginUiState(),
)

data class UserInputLoginUiState(
    var userNameOrEmail: TextFieldUiState = TextFieldUiState(),
    var password: TextFieldUiState = TextFieldUiState(),
)