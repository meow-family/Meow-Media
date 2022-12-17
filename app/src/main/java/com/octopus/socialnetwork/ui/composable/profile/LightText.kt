package com.octopus.socialnetwork.ui.composable.profile

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.octopus.socialnetwork.ui.theme.textPrimaryColor

@Composable
fun LightText(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.subtitle2,
        color = MaterialTheme.colors.textPrimaryColor,
    )
}