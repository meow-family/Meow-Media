package com.octopus.socialnetwork.ui.screen.register.uistate

data class RegisterUiState(
    val initPage: Int = 0,
    val userInfoForm: UserInfoFormUiState = UserInfoFormUiState(),
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isValidInputs: Boolean = false,
    val displayErrors: Boolean = false,
    val failedCreateAccount: Boolean = false,
)
