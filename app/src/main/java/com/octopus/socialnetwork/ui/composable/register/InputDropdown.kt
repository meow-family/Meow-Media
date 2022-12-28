package com.octopus.socialnetwork.ui.composable.register

import androidx.compose.foundation.layout.Column
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.composable.InputTextFieldValidation
import com.octopus.socialnetwork.ui.composable.TextFieldError
import com.octopus.socialnetwork.ui.screen.register.state.uistate.TextFieldUiState
import com.octopus.socialnetwork.ui.theme.textPrimaryColor


@Composable
@ExperimentalMaterialApi
fun InputDropdown(
    state: TextFieldUiState,
    options: List<String>,
    expanded: Boolean,
    showError: Boolean,
    onClick: (String) -> Unit,
    onDismissRequest: () -> Unit,
    onValueChange: (String) -> Unit,
    onExpandedChange: (Boolean) -> Unit,
) {
    Column() {
        ExposedDropdownMenuBox(
            expanded = expanded, onExpandedChange = onExpandedChange
        ) {
            InputTextFieldValidation(
                state = state,
                onChangeValue = onValueChange,
                placeholder = stringResource(R.string.gender),
                icon = Icons.Default.Person,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                },
                isReadOnly = true,
                showError = false
            )

            ExposedDropdownMenu(
                expanded = expanded, onDismissRequest = onDismissRequest
            ) {
                options.forEach { selectionOption ->
                    DropdownMenuItem(onClick = { onClick(selectionOption) }) {
                        Text(
                            text = selectionOption,
                            color = MaterialTheme.colors.textPrimaryColor
                        )
                    }
                }
            }

        }
        if (!state.isValid && showError) {
            state.error?.let { TextFieldError(textError = stringResource(it)) }
        }
    }

}