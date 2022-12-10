package com.octopus.socialnetwork.ui.composable.notifications

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.octopus.socialnetwork.ui.composable.Icons
import com.octopus.socialnetwork.ui.theme.Gray900_2

@Composable
fun NotificationIcon(
    icon: ImageVector,
    onClick: () -> Unit
) {
    Icons(
        imageVector = icon,
        tint = Gray900_2,
        modifier = Modifier
            .size(24.dp)
            .clickable { onClick() }
    )
}