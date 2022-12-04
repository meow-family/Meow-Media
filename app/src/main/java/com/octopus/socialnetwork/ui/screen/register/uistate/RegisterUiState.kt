package com.octopus.socialnetwork.ui.screen.register.uistate

data class RegisterUiState(
    val userInfoForm: UserInfoFormUiState = UserInfoFormUiState(),
    val isLoading: Boolean = false,
    val isValidInputs: Boolean = false,
    val displayErrors: Boolean = false,
)
