package com.octopus.socialnetwork.ui.screen.register.state

import com.octopus.socialnetwork.ui.screen.register.state.uistate.UserInfoFormUiState

data class RegisterUiState(
    val initPage: Int = 0,
    val userInfoForm: UserInfoFormUiState = UserInfoFormUiState(),
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isValidInputs: Boolean = false,
    val displayErrorsFirstStepRegistration: Boolean = false,
    val displayErrorsSecondStepRegistration: Boolean = false,
    val failedCreateAccount: Boolean = false,
)