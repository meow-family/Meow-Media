package com.octopus.socialnetwork.ui.composable.register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
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
import androidx.compose.ui.unit.dp
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.composable.InputTextField
import com.octopus.socialnetwork.ui.composable.SpacerVertical16
import com.octopus.socialnetwork.ui.composable.rememberDatePicker
import com.octopus.socialnetwork.ui.screen.register.uistate.UserInfoFormUiState


@Composable
@ExperimentalMaterialApi
fun SecondStepRegistration(
    userInfoForm: UserInfoFormUiState,
    onChangeFirstName: (String) -> Unit,
    onChangeLastName: (String) -> Unit,
    onChangeGender: (String) -> Unit,
    onChangeBirthday: (String) -> Unit,
) {

    var expandedDropdownMenu by remember { mutableStateOf(false) }
    val datePicker = rememberDatePicker(onChangeBirthday)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 32.dp)
    ) {

        InputTextField(
            placeholder = stringResource(R.string.first_name),
            icon = Icons.Default.Person,
            action = ImeAction.Next,
            value = userInfoForm.firstName.text,
            isPassword = false,
            onValueChange = onChangeFirstName,
        )
        SpacerVertical16()
        InputTextField(
            placeholder = stringResource(R.string.last_name),
            icon = Icons.Default.Person,
            action = ImeAction.Next,
            value = userInfoForm.lastName.text,
            isPassword = false,
            onValueChange = onChangeLastName,
        )
        SpacerVertical16()
        DropdownMenuApp(
            expanded = expandedDropdownMenu,
            onValueChange = onChangeGender,
            value = userInfoForm.gender.text,
            onExpandedChange = {
                expandedDropdownMenu = !expandedDropdownMenu
            },
            onDismissRequest = {
                expandedDropdownMenu = false
            },
            onClick = { selectedGender ->
                userInfoForm.gender.text = selectedGender
                expandedDropdownMenu = false
            }
        )
        SpacerVertical16()

        InputTextField(
            modifier = Modifier.clickable { datePicker.show() },
            isReadOnly = true,
            placeholder = stringResource(R.string.birthday),
            icon = Icons.Default.CalendarMonth,
            action = ImeAction.Done,
            value = userInfoForm.birthDate.text,
            onValueChange = onChangeBirthday,
        )
        Button(
            onClick = { datePicker.show() },
        ) {
        }


//
    }
}

