package com.octopus.socialnetwork.ui.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.theme.Shapes
import com.octopus.socialnetwork.ui.theme.heightDefaultButton
import com.octopus.socialnetwork.ui.theme.spacingMedium
import com.octopus.socialnetwork.ui.theme.textPrimaryColor
import com.octopus.socialnetwork.ui.theme.textThirdColor
import com.octopus.socialnetwork.ui.screen.register.state.uistate.TextFieldUiState
import com.octopus.socialnetwork.ui.theme.*

@Composable
fun InputTextFieldValidation(
    modifier: Modifier = Modifier,
    state: TextFieldUiState,
    placeholder: String,
    icon: ImageVector,
    showError: Boolean,
    onChangeValue: (String) -> Unit,
    isPassword: Boolean = false,
    isReadOnly: Boolean = false,
    isEnabled: Boolean = true,
    keyboardAction: ImeAction = ImeAction.Next,
    keyboardType: KeyboardType = KeyboardType.Text,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    OutlinedTextField(
        modifier = modifier
            .height(heightDefaultButton)
            .fillMaxWidth()
            .padding(horizontal = spacingMedium),
        shape = Shapes.large,
        value = state.text,
        readOnly = isReadOnly,
        singleLine = true,
        enabled = isEnabled,
        visualTransformation = if (isPassword) PasswordVisualTransformation()
        else VisualTransformation.None,
        onValueChange = onChangeValue,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = keyboardAction,
            keyboardType = keyboardType
        ),
        placeholder = {
            Text(
                text = placeholder, style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.textThirdColor
            )
        },
        leadingIcon = {
            Icon(
                icon,
                stringResource(id = R.string.icon_description, placeholder),
                tint = Color.Gray,
            )
        },
        trailingIcon = trailingIcon,
        textStyle = MaterialTheme.typography.h6,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            cursorColor = MaterialTheme.colors.primary,
            focusedBorderColor = MaterialTheme.colors.primary,
            unfocusedBorderColor = if (!state.isValid && showError) MaterialTheme.colors.error else Color.Gray,
            focusedLabelColor = MaterialTheme.colors.primary,
            errorBorderColor = MaterialTheme.colors.error,
            textColor = MaterialTheme.colors.textPrimaryColor
        ),

        )

    if (!state.isValid && showError) {
        state.error?.let { TextFieldError(textError = stringResource(it)) }
    }
}

