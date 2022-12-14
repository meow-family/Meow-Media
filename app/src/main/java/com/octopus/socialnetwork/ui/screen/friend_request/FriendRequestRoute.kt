package com.octopus.socialnetwork.ui.screen.friend_request

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


private const val ROUTE = "friend_request"

fun NavController.navigateToFriendRequests() {
    navigate(ROUTE)
}


fun NavGraphBuilder.friendRequestsRoute(navController: NavController) {
    composable(ROUTE) {
        FriendRequestScreen(navController)
    }
}
