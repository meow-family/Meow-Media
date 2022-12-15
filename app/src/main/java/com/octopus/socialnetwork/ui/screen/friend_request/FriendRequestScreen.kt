package com.octopus.socialnetwork.ui.screen.friend_request

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.composable.AppBar
import com.octopus.socialnetwork.ui.composable.Loading
import com.octopus.socialnetwork.ui.composable.friend_requests.FriendRequestItem
import com.octopus.socialnetwork.ui.screen.friend_request.state.FriendRequestUiState
import com.octopus.socialnetwork.ui.screen.notifications.state.NotificationItemsUiState
import com.octopus.socialnetwork.ui.theme.DividerColor
import com.octopus.socialnetwork.ui.util.extensions.setScreenDestinationOnClickNotification

@Composable
fun FriendRequestScreen(
    navController: NavController,
    viewModel: FriendRequestViewModel = hiltViewModel(),
) {

    val state by viewModel.state.collectAsState()
    FriendRequestContent(
        state = state,
        onClickNotification = { notification ->
            notification.notificationDetails.type
                .setScreenDestinationOnClickNotification(navController, notification)

        }
    ) { navController.popBackStack() }
}


@Composable
private fun FriendRequestContent(
    state: FriendRequestUiState,
    onClickNotification: (NotificationItemsUiState) -> Unit,
    onClickBack: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
    ) {

        AppBar(onClickBack, stringResource(R.string.friend_requests))
        Divider(color = DividerColor, thickness = 1.dp)
        if (state.isLoading) {
            Loading()
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(state.friendRequests) {
                    FriendRequestItem(it.fullName,it.profileAvatar)
                }
            }
        }

    }
}

