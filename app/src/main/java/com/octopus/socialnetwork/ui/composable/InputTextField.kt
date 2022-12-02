package com.octopus.socialnetwork.ui.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InputTextField(
    placeholder: String,
    icon: ImageVector,
    value: String,
    isPassword: Boolean = false,
    isReadOnly: Boolean = false,
    onValueChange: (String) -> Unit,
    trailingIcon: @Composable() (() -> Unit)? = null,
    action: ImeAction
) {
    OutlinedTextField(
        modifier = Modifier
            .height(48.dp)
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        shape = RoundedCornerShape(24.dp),
        value = value,
        readOnly = isReadOnly,
        singleLine = true,
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(imeAction = action),
        placeholder = { Text(text = placeholder, fontSize = 14.sp, color = Color.LightGray) },
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

            ),
        textStyle = TextStyle(color = Color.Black, fontSize = 14.sp)
    )
}