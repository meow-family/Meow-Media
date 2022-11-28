package com.octopus.socialnetwork.ui.screen.signUp.composable

import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.composable.InputTextField


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropdownMenuApp(
    options: List<String> = listOf("Male", "female"),
    expanded: Boolean,
    value: String,
    onClick: (String) -> Unit,
    onDismissRequest: () -> Unit,
    onValueChange: (String) -> Unit,
    onExpandedChange: (Boolean) -> Unit,

    ) {
    ExposedDropdownMenuBox(
        expanded = expanded, onExpandedChange = onExpandedChange
    ) {
        InputTextField(
            placeholder = stringResource(R.string.gender),
            icon = Icons.Default.Person,
            action = ImeAction.Next,
            value = value,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            isReadOnly = true,
            onValueChange = onValueChange,
        )
        ExposedDropdownMenu(
            expanded = expanded, onDismissRequest = onDismissRequest
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(onClick = { onClick(selectionOption) }) {
                    Text(text = selectionOption)
                }
            }
        }
    }
}