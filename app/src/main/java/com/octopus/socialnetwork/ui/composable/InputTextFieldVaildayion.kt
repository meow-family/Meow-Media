package com.octopus.socialnetwork.ui.composable

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.octopus.socialnetwork.ui.screen.register.uistate.TextFieldState

@Composable
fun InputTextFieldValidation(
    state: TextFieldState,
    placeholder: String,
    icon: ImageVector,
    isPassword: Boolean = false,
    onChangeEmail: (String) -> Unit,
    isReadOnly: Boolean = false,
    trailingIcon: @Composable() (() -> Unit)? = null,
    action: ImeAction = ImeAction.Next,
) {
    OutlinedTextField(
        modifier = Modifier
            .height(48.dp)
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .onFocusChanged { focusState ->
                state.onFocusChange(focusState.isFocused)
                if (!focusState.isFocused) {
                    state.enableShowErrors()
                }
            },
        shape = RoundedCornerShape(24.dp),
        value = state.state.text,
        readOnly = isReadOnly,
        singleLine = true,
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        onValueChange = onChangeEmail,
//        {
//            Log.v("ameer",it)
//            state.state.text = it
//        },
        keyboardOptions = KeyboardOptions(imeAction = action),
        placeholder = { Text(text = placeholder, fontSize = 14.sp, color = Color.LightGray) },
        leadingIcon = {
            Icon(
                icon,
                contentDescription = "$placeholder Icon",
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
//    if (state.getError())
//        TextFieldError(textError = "error")
        state.getError()?.let { error -> TextFieldError(textError = error) }
}

@Composable
fun TextFieldError(textError: String) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = textError,
            modifier = Modifier.fillMaxWidth(),
            fontSize = 12.sp,
            color = Color.Red,
            fontWeight = FontWeight.Light,
        )
    }
}