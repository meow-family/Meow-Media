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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.octopus.socialnetwork.ui.theme.Shapes
import com.octopus.socialnetwork.ui.theme.heightDefaultButton
import com.octopus.socialnetwork.ui.theme.spacingSmall
import com.octopus.socialnetwork.ui.theme.textSecondaryColor
import com.octopus.socialnetwork.ui.theme.textThirdColor

@Composable
fun InputTextField(
    placeholder: String,
    icon: ImageVector,
    value: String,
    modifier: Modifier = Modifier,
    isPassword: Boolean = false,
    isReadOnly: Boolean = false,
    onValueChange: (String) -> Unit,
    trailingIcon: @Composable() (() -> Unit)? = null,
    action: ImeAction
) {
    OutlinedTextField(
        modifier = modifier
            .height(heightDefaultButton)
            .fillMaxWidth()
            .padding(horizontal = spacingSmall),
        shape = Shapes.large,
        value = value,
        readOnly = isReadOnly,
        singleLine = true,
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(imeAction = action),
        placeholder = {
            Text(
                text = placeholder,
                style = MaterialTheme.typography.h6.copy(color = MaterialTheme.colors.textSecondaryColor),
                )
        },
        leadingIcon = {
            Icon(
                icon,
                contentDescription = "$placeholder IconDTO",
                tint = Color.Gray,
            )
        },
        trailingIcon = trailingIcon,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Red,
            unfocusedBorderColor = Color.Gray,
            focusedLabelColor = Color.Red,
            cursorColor = Color.Red,
            textColor = MaterialTheme.colors.textSecondaryColor
            ),
        textStyle = MaterialTheme.typography.h6
    )
}