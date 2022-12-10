package com.octopus.socialnetwork.ui.screen.notifications

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


private const val ROUTE = "notification"

fun NavController.navigateToNotification() {
    navigate(ROUTE)
}


fun NavGraphBuilder.notificationRoute(navController: NavController) {
    composable(ROUTE) {
        NotificationsScreen(navController)
    }
}
