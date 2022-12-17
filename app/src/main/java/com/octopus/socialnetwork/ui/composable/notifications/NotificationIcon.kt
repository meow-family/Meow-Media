package com.octopus.socialnetwork.ui.composable.notifications

import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.octopus.socialnetwork.ui.composable.Icons
import com.octopus.socialnetwork.ui.theme.textPrimaryColor

@Composable
fun NotificationIcon(
    icon: ImageVector,
) {
    Icons(
        imageVector = icon,
        tint = MaterialTheme.colors.textPrimaryColor,
        modifier = Modifier.size(24.dp)
    )
}