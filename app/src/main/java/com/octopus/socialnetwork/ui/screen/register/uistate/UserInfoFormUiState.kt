package com.octopus.socialnetwork.ui.screen.register.uistate


data class UserInfoFormUiState(
    var userName: TextFieldUiState = TextFieldUiState(),
    var email: TextFieldUiState = TextFieldUiState(),
    var reEmail: TextFieldUiState = TextFieldUiState(),
    var password: TextFieldUiState = TextFieldUiState(),
    var firstName: TextFieldUiState = TextFieldUiState(),
    var lastName: TextFieldUiState = TextFieldUiState(),
    var gender: TextFieldUiState = TextFieldUiState(),
    var birthday: TextFieldUiState = TextFieldUiState(),
)
