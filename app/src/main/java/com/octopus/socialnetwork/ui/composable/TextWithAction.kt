package com.octopus.socialnetwork.ui.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextWithAction(
    text: String,
    textAction: String,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 16.dp),

        ) {
        Text(
            text = text,
            fontSize = 14.sp,
            color = Color.Gray,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Light,
        )
        
        Text(
            modifier = Modifier
                .clickable { onClick }
                .padding(4.dp)
                .clip(RoundedCornerShape(24.dp)),
            text = textAction,
            fontSize = 14.sp,
            color = Color.Red,
            fontFamily = FontFamily.SansSerif,
        )

    }


}