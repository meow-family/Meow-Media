package com.octopus.socialnetwork.ui.composable

import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.octopus.socialnetwork.ui.theme.Gray900_2

@Composable
fun Icons(imageVector: ImageVector,modifier: Modifier) {
    Icon(
        imageVector = imageVector,
        contentDescription = "icon",
        tint = Gray900_2,
        modifier = modifier

    )

}