package com.octopus.socialnetwork.ui.screen.signUp.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StepText(text: String, isDone: Boolean) {
    Text(
        text = text, modifier = Modifier
            .background(
                if (isDone) Color.Red else Color(0xFFF4F4F4),
                shape = RoundedCornerShape(20.dp)
            )
            .padding(vertical = 3.dp, horizontal = 8.dp),
        style = TextStyle(fontSize = 14.sp, color = if (isDone) Color.White else Color.Gray)
    )
}