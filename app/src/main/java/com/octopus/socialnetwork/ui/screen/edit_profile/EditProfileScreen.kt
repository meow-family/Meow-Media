package com.octopus.socialnetwork.ui.screen.edit_profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.composable.AppBar
import com.octopus.socialnetwork.ui.composable.CustomButton
import com.octopus.socialnetwork.ui.composable.SpacerVertical32
import com.octopus.socialnetwork.ui.composable.profile.EditTextField
import com.octopus.socialnetwork.ui.composable.profile.ProfileInformation
import com.octopus.socialnetwork.ui.composable.profile.TopBarArrow
import com.octopus.socialnetwork.ui.screen.edit_profile.uistate.EditProfileUiState


@Preview(showSystemUi = true)
@Composable
fun EditProfileScreen(
    viewModel: EditProfileViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()
    EditProfileContent(
        state = state,
        onChangeFirstName = viewModel::onChangeFirstName,
        onChangeLastName = viewModel::onChangeLastName,
        onChangeEmail = viewModel::onChangeEmail,
        onChangeCurrentPassword = viewModel::onChangeCurrentPassword,
        onChangeNewPassword = viewModel::onChangeNewPassword,
        onClickSave = viewModel::onClickSave,
        onClickBack = { }

    )
}

@Composable
private fun EditProfileContent(
    state: EditProfileUiState,
    onChangeFirstName: (String) -> Unit,
    onChangeLastName: (String) -> Unit,
    onChangeEmail: (String) -> Unit,
    onChangeCurrentPassword: (String) -> Unit,
    onChangeNewPassword: (String) -> Unit,
    onClickSave: () -> Unit,
    onClickBack: () -> Unit


) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        item {
            AppBar(onClickBack = onClickBack, title = stringResource(R.string.edit_profile))
            ProfileInformation(
                painterResource(id = R.drawable.black),
                painterResource(id = R.drawable.iron_man),
                stringResource(R.string.edit_profile)
            )
            EditTextField(
                title = stringResource(R.string.first_name),
                keyboardOption = KeyboardOptions(keyboardType = KeyboardType.Text),
                value = state.firstName,
                onValueChange = onChangeFirstName
            )
            EditTextField(
                title = stringResource(R.string.last_name),
                keyboardOption = KeyboardOptions(keyboardType = KeyboardType.Text),
                value = state.lastName,
                onValueChange = onChangeLastName
            )
            EditTextField(
                title = stringResource(R.string.email),
                keyboardOption = KeyboardOptions(keyboardType = KeyboardType.Email),
                value = state.email,
                onValueChange = onChangeEmail
            )
            EditTextField(
                title = stringResource(R.string.current_password),
                keyboardOption = KeyboardOptions(keyboardType = KeyboardType.Password),
                value = state.currentPassword,
                onValueChange = onChangeCurrentPassword
            )
            EditTextField(
                title = stringResource(R.string.new_password),
                keyboardOption = KeyboardOptions(keyboardType = KeyboardType.Password),
                value = state.newPassword,
                onValueChange = onChangeNewPassword
            )
            SpacerVertical32()
            CustomButton(
                text = stringResource(R.string.save),
                onClick = onClickSave
            )
            SpacerVertical32()
        }


    }
}






