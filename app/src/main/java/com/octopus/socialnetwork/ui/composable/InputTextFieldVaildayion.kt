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
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.screen.register.uistate.TextFieldState
import com.octopus.socialnetwork.ui.theme.Shapes
import com.octopus.socialnetwork.ui.theme.heightInput
import com.octopus.socialnetwork.ui.theme.spacingSmall
import com.octopus.socialnetwork.ui.theme.textThirdColor

@Composable
fun InputTextFieldValidation(
    state: TextFieldState,
    placeholder: String,
    icon: ImageVector,
    onChangeEmail: (String) -> Unit,
    isPassword: Boolean = false,
    isReadOnly: Boolean = false,
    action: ImeAction = ImeAction.Next,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    OutlinedTextField(
        modifier = Modifier
            .height(heightInput)
            .fillMaxWidth()
            .padding(horizontal = spacingSmall)
            .onFocusChanged {
//                    focusState ->
//                state.onFocusChange(focusState.isFocused)
//                if (!focusState.isFocused) {
//                    state.enableShowErrors()
//                }
            },
        shape = Shapes.large,
        value = state.state.text,
        readOnly = isReadOnly,
        singleLine = true,
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        onValueChange = onChangeEmail,
        keyboardOptions = KeyboardOptions(imeAction = action),
        placeholder = {
            Text(
                text = placeholder,
                style = MaterialTheme.typography.h6,
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
            unfocusedBorderColor = if (!state.state.isValid && state.showError) MaterialTheme.colors.error else Color.Gray,
            focusedLabelColor = MaterialTheme.colors.primary,
            errorBorderColor = MaterialTheme.colors.error,
            textColor = MaterialTheme.colors.textThirdColor
        ),

        )

    state.getError()?.let { error -> TextFieldError(textError = stringResource(id = error)) }
}

