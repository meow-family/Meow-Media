package com.octopus.socialnetwork.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.octopus.socialnetwork.ui.theme.PoppinsTypography

@Composable
fun CircleShapeWithText(numberOfMessages:Int){
    Box(
        modifier = Modifier
            .size(16.dp)
            .clip(CircleShape)
            .background(color = Color.Red),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "$numberOfMessages", color = Color.White, fontSize = PoppinsTypography.overline.fontSize )
    }
}