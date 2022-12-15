package com.octopus.socialnetwork.ui.composable.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.composable.SpaceHorizontally8dp
import com.octopus.socialnetwork.ui.composable.notifications.IconWithCount
import com.octopus.socialnetwork.ui.composable.shadowLightBlack


@Composable
fun TopBar(
    notificationsCount: Int,
    firendRequestsCount: Int,
    onClickNotifications: () -> Unit,
    onClickFriendRequests: () -> Unit,
) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(56.dp)
            .shadowLightBlack()
            .background(MaterialTheme.colors.background)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween

    ) {
        AppName(text = stringResource(R.string.octopusyan))

        Row {
            IconWithCount(
                count = firendRequestsCount,
                icon = Icons.Default.Person,
                onClick = onClickFriendRequests
            )
            SpaceHorizontally8dp()
            IconWithCount(
                count = notificationsCount,
                icon = Icons.Default.Notifications,
                onClick = onClickNotifications
            )
        }
    }

}