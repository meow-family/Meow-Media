package com.octopus.socialnetwork.ui.composable.register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.composable.InputTextFieldValidation
import com.octopus.socialnetwork.ui.composable.SpacerVertical16
import com.octopus.socialnetwork.ui.screen.register.uistate.UserInfoFormUiState
import com.octopus.socialnetwork.ui.theme.spacingExtraLarge


@Composable
fun FirstStepRegistration(
    userInfoForm: UserInfoFormUiState,
    showError: Boolean,
    onChangeUserName: (String) -> Unit,
    onChangeEmail: (String) -> Unit,
    onChangeReEmail: (String) -> Unit,
    onChangePassword: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = spacingExtraLarge)
    ) {

        InputTextFieldValidation(
            state = userInfoForm.userName,
            onChangeValue = onChangeUserName,
            placeholder = stringResource(R.string.username),
            icon = Icons.Default.Person,
            action = ImeAction.Next,
            showError = showError
        )
        SpacerVertical16()
        InputTextFieldValidation(
            state = userInfoForm.email,
            onChangeValue = onChangeEmail,
            placeholder = stringResource(R.string.email),
            icon = Icons.Default.Email,
            action = ImeAction.Next,
            showError = showError
        )
        SpacerVertical16()
        InputTextFieldValidation(
            state = userInfoForm.reEmail,
            onChangeValue = onChangeReEmail,
            placeholder = stringResource(R.string.re_email),
            icon = Icons.Default.Email,
            action = ImeAction.Next,
            showError = showError
        )
        SpacerVertical16()
        InputTextFieldValidation(
            state = userInfoForm.password,
            onChangeValue = onChangePassword,
            placeholder = stringResource(R.string.password),
            icon = Icons.Default.Lock,
            action = ImeAction.Next,
            isPassword = true,
            showError = showError
        )

    }


}