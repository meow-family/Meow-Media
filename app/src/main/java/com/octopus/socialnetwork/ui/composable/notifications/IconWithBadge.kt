package com.octopus.socialnetwork.ui.composable.notifications

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Badge
import androidx.compose.material.BadgedBox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.octopus.socialnetwork.ui.composable.Icons
import com.octopus.socialnetwork.ui.theme.Gray900_2
import com.octopus.socialnetwork.ui.theme.Red500
import com.octopus.socialnetwork.ui.theme.White50


@Composable
fun NotificationsWithBadge(
    badgeCount: Int,
    icon: ImageVector,
    onClick: () -> Unit
) {
    if(badgeCount > 0) {
        BadgedBox(
            badge = {
                Column(
                    Modifier.wrapContentSize(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceBetween

                ) {
                    Spacer(modifier = Modifier.height(10.dp))

                    Badge(
                        modifier = Modifier.border(width = 1.dp, color = MaterialTheme.colors.background, shape = CircleShape),
                        backgroundColor = Red500
                    ){
                        Text(
                            text = setBadgeCountValue(badgeCount),
                            textAlign = TextAlign.Center,
                            color = White50,
                            fontWeight = FontWeight.Bold,
                            fontSize = 8.sp,
                            modifier = Modifier.padding(start = 1.dp, end = 1.dp, top = 1.dp),
                        )
                    }
                }
            },
            modifier = Modifier.padding(end = 16.dp)
        ) {
            BadgeIcon(icon = icon, onClick = onClick)
        }
    } else {
        BadgeIcon(icon = icon, onClick = onClick)
    }

}


@Composable
fun BadgeIcon(
    icon: ImageVector,
    onClick: () -> Unit
){
    Icons(
        imageVector = icon,
        tint = Gray900_2,
        modifier = Modifier
            .size(24.dp)
            .clickable { onClick() }
    )
}


fun setBadgeCountValue(badgeCount: Int): String = if(badgeCount in 1..99) badgeCount.toString() else "+99"