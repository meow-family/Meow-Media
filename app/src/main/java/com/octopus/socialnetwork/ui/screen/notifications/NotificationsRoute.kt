package com.octopus.socialnetwork.ui.screen.notifications

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


private const val ROUTE = "notifications"

fun NavController.navigateToNotificationsScreen(){
    navigate(ROUTE)
}

fun NavGraphBuilder.notificationsRoute(navController: NavController) {
    composable(ROUTE) { NotificationsScreen(navController) }
}