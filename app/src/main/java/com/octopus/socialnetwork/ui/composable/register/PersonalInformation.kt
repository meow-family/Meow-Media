package com.octopus.socialnetwork.ui.composable.register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.composable.InputTextFieldValidation
import com.octopus.socialnetwork.ui.composable.SpacerVertical16
import com.octopus.socialnetwork.ui.composable.rememberDatePicker
import com.octopus.socialnetwork.ui.screen.register.uistate.UserInfoFormUiState
import com.octopus.socialnetwork.ui.theme.spacingExtraLarge

@Composable
@ExperimentalMaterialApi
fun PersonalInformation(
    userInfoForm: UserInfoFormUiState,
    showError: Boolean,
    onChangeFirstName: (String) -> Unit,
    onChangeLastName: (String) -> Unit,
    onChangeGender: (String) -> Unit,
    onChangeBirthday: (String) -> Unit,
) {

    var expandedDropdownMenu by remember { mutableStateOf(false) }
    val datePicker = rememberDatePicker(onChangeBirthday)
    val gender: List<String> = listOf("male", "female")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = spacingExtraLarge)
    ) {

        InputTextFieldValidation(
            state = userInfoForm.firstName,
            onChangeValue = onChangeFirstName,
            placeholder = stringResource(R.string.first_name),
            icon = Icons.Default.Person,
            showError = showError
        )
        SpacerVertical16()
        InputTextFieldValidation(
            state = userInfoForm.lastName,
            onChangeValue = onChangeLastName,
            placeholder = stringResource(R.string.last_name),
            icon = Icons.Default.Person,
            showError = showError
        )
        SpacerVertical16()
        InputDropdown(
            expanded = expandedDropdownMenu,
            onValueChange = onChangeGender,
            options = gender,
            state = userInfoForm.gender,
            onExpandedChange = {
                expandedDropdownMenu = !expandedDropdownMenu
            },
            onDismissRequest = {
                expandedDropdownMenu = false
            },
            onClick = { selectedGender ->
                userInfoForm.gender.text = selectedGender
                onChangeGender(selectedGender)
                expandedDropdownMenu = false
            },
            showError = showError
        )
        SpacerVertical16()
        InputTextFieldValidation(
            Modifier.clickable { datePicker.show() },
            state = userInfoForm.birthDate,
            onChangeValue = onChangeBirthday,
            placeholder = stringResource(R.string.birthday),
            icon = Icons.Default.CalendarMonth,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            isReadOnly = true,
            isEnabled = false,
            showError = showError
        )


    }
}

