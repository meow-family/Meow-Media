package com.octopus.socialnetwork.ui.screen.notifications

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


const val ROUTE_NOTIFICATION = "notifications"

fun NavController.navigateToNotificationsScreen(){
    navigate(ROUTE_NOTIFICATION)
}

fun NavGraphBuilder.notificationsRoute(navController: NavController) {
    composable(ROUTE_NOTIFICATION) {
        NotificationsScreen(navController)
    }
}