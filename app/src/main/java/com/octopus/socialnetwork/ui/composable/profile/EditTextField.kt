package com.octopus.socialnetwork.ui.composable.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EditTextField(
    title: String,
    keyboardOption: KeyboardOptions
) {
    var inputText by remember { mutableStateOf("") }
    Column(modifier = Modifier.padding(horizontal = 24.dp, vertical = 15.dp)) {
        Text(
            text = title,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            color = Color.Gray
        )
        BasicTextField(
            value = inputText,
            onValueChange = { inputText = it },
            keyboardOptions = keyboardOption
        )

        Divider(
            color = Color(color = 0xFFBCBCBC), modifier = Modifier
                .fillMaxWidth()
                .padding(top = 7.dp)
                .height(1.dp)
                .alpha(1f)
        )
    }
}