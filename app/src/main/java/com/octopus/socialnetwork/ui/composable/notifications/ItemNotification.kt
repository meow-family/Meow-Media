package com.octopus.socialnetwork.ui.composable.notifications

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.screen.notifications.state.NotificationDetailsUiState
import com.octopus.socialnetwork.ui.screen.notifications.state.NotificationsUiState
import com.octopus.socialnetwork.ui.theme.Gray700
import com.octopus.socialnetwork.ui.theme.LightBlack_65

@Composable
fun ItemNotification(
    notification: NotificationDetailsUiState,
    onClick: (Int) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile_image),
            contentDescription = stringResource(R.string.profile_image),
            modifier = Modifier
                .clip(CircleShape)
                .size(44.dp),
            contentScale = ContentScale.Crop,
        )
        Column(modifier = Modifier
            .padding(start = 16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Row() {
                Text(
                    text = "Ali Omar",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = LightBlack_65,
                )
                Text(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp),
                    text = "has started following you has started following you",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = LightBlack_65,
                )
            }

            Text(
                text = "9:01am",
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                color = Gray700,
            )
        }
    }
}