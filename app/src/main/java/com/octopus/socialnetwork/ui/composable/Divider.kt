package com.octopus.socialnetwork.ui.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material.Divider
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Divider(modifier: Modifier = Modifier) {
    Divider(
        color = Color(color = 0xFFBCBCBC),
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .alpha(0.2f)
    )
}