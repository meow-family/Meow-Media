package com.octopus.socialnetwork.ui.composable.register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.composable.InputTextFieldValidation
import com.octopus.socialnetwork.ui.composable.SpacerVertical16
import com.octopus.socialnetwork.ui.screen.register.state.uistate.UserInfoFormUiState
import com.octopus.socialnetwork.ui.theme.spacingExtraLarge


@Composable
fun AccountInformation(
    state: UserInfoFormUiState,
    showError: Boolean,
    onChangeUserName: (String) -> Unit,
    onChangeEmail: (String) -> Unit,
    onChangeReEmail: (String) -> Unit,
    onChangePassword: (String) -> Unit,
    onClickShowPassword: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = spacingExtraLarge)
    ) {

        InputTextFieldValidation(
            state = state.userName,
            onChangeValue = onChangeUserName,
            placeholder = stringResource(R.string.username),
            icon = Icons.Default.Person,
            showError = showError
        )
        SpacerVertical16()
        InputTextFieldValidation(
            state = state.email,
            onChangeValue = onChangeEmail,
            placeholder = stringResource(R.string.email),
            icon = Icons.Default.Email,
            showError = showError
        )
        SpacerVertical16()
        InputTextFieldValidation(
            state = state.reEmail,
            onChangeValue = onChangeReEmail,
            placeholder = stringResource(R.string.re_email),
            icon = Icons.Default.Email,
            showError = showError
        )
        SpacerVertical16()
        InputTextFieldValidation(
            state = state.password,
            onChangeValue = onChangePassword,
            placeholder = stringResource(R.string.password),
            icon = Icons.Default.Lock,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
            isPassword = !state.showPassword,
            showError = showError,
            trailingIcon = {
                IconButton(onClick = onClickShowPassword) {
                    if (state.showPassword ) {
                        Icon(Icons.Filled.Visibility, contentDescription = null)
                    } else {
                        Icon(Icons.Filled.VisibilityOff, contentDescription = null)
                    }
                }
            }
        )

    }


}