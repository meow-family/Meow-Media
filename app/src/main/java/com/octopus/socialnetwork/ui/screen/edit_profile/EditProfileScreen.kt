package com.octopus.socialnetwork.ui.screen.edit_profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.composable.*
import com.octopus.socialnetwork.ui.composable.profile.EditTextField
import com.octopus.socialnetwork.ui.composable.profile.TopBarArrow
import com.octopus.socialnetwork.ui.screen.edit_profile.uistate.EditProfileUiState

@Composable
fun EditProfileScreen(
    navController: NavController,
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
        if (state.isLoading) {
            item {
                Loading()
            }
        } else {
            item {
                TopBarArrow(
                    title = stringResource(id = R.string.edit_profile),
                    onClickBack = onClickBack
                )
                EditProfileInformation(
                    backImageProfile = customImageLoad(state.profileAvatar),
                    profileImage = customImageLoad(state.profileCover),
                    onEdit = { })
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
                    onClick = onClickSave,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                SpacerVertical32()
            }
        }
    }
}