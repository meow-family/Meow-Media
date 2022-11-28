package com.octopus.socialnetwork.ui.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun LoginButton( login: ()-> Unit){
    Button(
        onClick = login,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(44.dp),
        shape = RoundedCornerShape(24.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)

    ) {
        Text(text = "Login", color = Color.White )
    }
}