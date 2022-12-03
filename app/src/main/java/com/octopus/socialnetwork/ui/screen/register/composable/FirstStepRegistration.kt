package com.octopus.socialnetwork.ui.screen.register.composable

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
import androidx.compose.ui.unit.dp
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.composable.InputTextField
import com.octopus.socialnetwork.ui.composable.InputTextFieldValidation
import com.octopus.socialnetwork.ui.composable.SpacerVertical16
import com.octopus.socialnetwork.ui.screen.register.uistate.TextFieldState
import com.octopus.socialnetwork.ui.screen.register.uistate.UserInfoFormUiState


@Composable
fun FirstStepRegistration(
    userInfoForm: UserInfoFormUiState,
    onChangeUserName: (String) -> Unit,
    onChangeEmail: (String) -> Unit,
    emailState: TextFieldState,
    onChangeReEmail: (String) -> Unit,
    onChangePassword: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 32.dp)
    ) {
        InputTextField(
            placeholder = stringResource(R.string.username),
            icon = Icons.Default.Person,
            action = ImeAction.Next,
            value = userInfoForm.userName.text,
            onValueChange = onChangeUserName,
        )
//        SpacerVertical16()
        InputTextFieldValidation(
            emailState,
            onChangeEmail = onChangeEmail,
            placeholder = stringResource(R.string.email),
            icon = Icons.Default.Email,
            action = ImeAction.Next,
        )
//        SpacerVertical16()
//        InputTextField(
//            placeholder = stringResource(R.string.re_email),
//            icon = Icons.Default.Email,
//            action = ImeAction.Next,
//            value = userInfoForm.reEmail.text,
//            onValueChange = onChangeReEmail,
//        )
//        SpacerVertical16()
//        InputTextField(
//            placeholder = stringResource(R.string.password),
//            icon = Icons.Default.Lock,
//            action = ImeAction.Next,
//            value = userInfoForm.password.text,
//            isPassword = true,
//            onValueChange = onChangePassword,
//        )
    }


}