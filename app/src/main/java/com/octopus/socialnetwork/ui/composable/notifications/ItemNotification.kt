package com.octopus.socialnetwork.ui.composable.notifications

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.composable.MultiTextStyle
import com.octopus.socialnetwork.ui.screen.notifications.state.NotificationItemsUiState
import com.octopus.socialnetwork.ui.theme.Gray700
import com.octopus.socialnetwork.ui.util.extensions.convertTimeCreatedToDate
import com.octopus.socialnetwork.ui.util.extensions.setNotificationsTitle


@Composable
fun ItemNotification(
    notification: NotificationItemsUiState,
    onClickNotification: (NotificationItemsUiState) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onClickNotification(notification) },
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = notification.posterDetails.posterAvatar),
            contentDescription = stringResource(R.string.profile_image),
            modifier = Modifier.clip(CircleShape).size(44.dp),
            contentScale = ContentScale.Crop,
        )
        Column(modifier = Modifier.padding(start = 16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            MultiTextStyle(
                name = notification.posterDetails.posterFullName,
                title =  stringResource(id = notification.notificationDetails.type.setNotificationsTitle()),
            )

            Text(
                text = notification.notificationDetails.timeCreated.convertTimeCreatedToDate(),
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                color = Gray700,
            )
        }
    }
}