package com.octopus.socialnetwork.ui.screen.notifications

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.octopus.socialnetwork.ui.navigation.DetailsRoute


private const val ROUTE = DetailsRoute.Notifications

fun NavController.navigateToNotificationsScreen(){
    navigate(ROUTE)
}

fun NavGraphBuilder.notificationsRoute(navController: NavController) {
    composable(ROUTE) { NotificationsScreen(navController) }
}