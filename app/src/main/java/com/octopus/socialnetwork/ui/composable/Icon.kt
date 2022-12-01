package com.octopus.socialnetwork.ui.composable

import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.octopus.socialnetwork.R

@Composable
fun Icons(imageVector: ImageVector,tint: Color ,modifier: Modifier) {
    Icon(
        imageVector = imageVector,
        contentDescription = stringResource(R.string.icon),
        tint =tint,
        modifier = modifier

    )

}