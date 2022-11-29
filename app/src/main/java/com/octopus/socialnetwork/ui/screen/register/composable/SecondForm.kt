package com.octopus.socialnetwork.ui.screen.register.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
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


@Composable
fun SecondForm() {

    var expandedDropdownMenu by remember { mutableStateOf(false) }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var birthday by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 32.dp)
    ) {


        InputTextField(
            placeholder = stringResource(R.string.first_name),
            icon = Icons.Default.Person,
            action = ImeAction.Next,
            value = firstName,
            isPassword = true,
            onValueChange = {
                firstName = it
            },
        )
        SpacerVertical16()
        InputTextField(
            placeholder = stringResource(R.string.last_name),
            icon = Icons.Default.Person,
            action = ImeAction.Next,
            value = lastName,
            isPassword = true,
            onValueChange = {
                lastName = it
            },
        )
        SpacerVertical16()
        DropdownMenuApp(
            expanded = expandedDropdownMenu,
            onValueChange = {},
            value = gender,
            onExpandedChange = {
                expandedDropdownMenu = !expandedDropdownMenu
            },
            onDismissRequest = {
                expandedDropdownMenu = false
            },
            onClick = { selectedGender ->
                gender = selectedGender
                expandedDropdownMenu = false
            }
        )
        SpacerVertical16()
        InputTextField(
            placeholder = stringResource(R.string.birthday),
            icon = Icons.Default.Person,
            action = ImeAction.Done,
            value = birthday,
            onValueChange = {
                birthday = it
            },
        )

    }
}