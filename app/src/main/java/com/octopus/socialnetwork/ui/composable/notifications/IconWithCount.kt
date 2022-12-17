package com.octopus.socialnetwork.ui.composable.notifications

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.octopus.socialnetwork.ui.theme.Red500
import com.octopus.socialnetwork.ui.theme.White50
import com.octopus.socialnetwork.ui.util.extensions.setBadgeCountValue


@Composable
fun IconWithCount(
    count: Int,
    icon: ImageVector,
) {
    if (count > 0) {
        Box {
            Box(
                modifier = Modifier
                    .clip(CircleShape).background(color = Red500).zIndex(1f).size(12.dp)
                    .align(alignment = Alignment.TopEnd)
            ) {
                Text(
                    modifier = Modifier.fillMaxSize(),
                    text = setBadgeCountValue(count),
                    textAlign = TextAlign.Center,
                    color = White50,
                    fontWeight = FontWeight.Bold,
                    fontSize = 8.sp
                )
            }
            NotificationIcon(icon = icon)
        }
    } else {
        NotificationIcon(icon = icon)
    }

}



