package com.octopus.socialnetwork.ui.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InputLoginTextField(
    value: String,
    onTextChange: (String) -> Unit,
    icon: ImageVector,
    placeholder: String,
    modifire: Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onTextChange,
        maxLines = 1,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Black,
            unfocusedBorderColor = Color.Gray,
            cursorColor = Color.Red

        ),

        singleLine = true,
        shape = RoundedCornerShape(24.dp),
        placeholder = { Text(text = placeholder, fontSize = 14.sp, color = Color.LightGray) },
        modifier = modifire
            .height(50.dp).fillMaxWidth().padding(horizontal = 8.dp, ),
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = ""
            )

        },
        textStyle = TextStyle(color = Color.Black, fontSize = 14.sp)
    )
}