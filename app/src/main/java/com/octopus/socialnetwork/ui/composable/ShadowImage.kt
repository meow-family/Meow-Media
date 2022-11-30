package com.octopus.socialnetwork.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

@Composable
fun ShadowImage(){
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .clip(RectangleShape)
                .zIndex(1f)
                .background(Brush.verticalGradient(listOf(Color.Transparent, Color.White)))

        )
    }
}