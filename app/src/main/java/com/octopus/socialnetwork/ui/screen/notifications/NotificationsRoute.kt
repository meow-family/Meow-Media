package com.octopus.socialnetwork.ui.screen.notifications

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument


private const val ROUTE = "notifications"
fun NavController.navigateToNotificationsScreen(userId: Int){
    navigate("$ROUTE/$userId")
}
fun NavGraphBuilder.notificationsRoute(navController: NavController) {
    composable("$ROUTE/{${NotificationsScreenArgs.USER_ID}}",
        arguments = listOf(
            navArgument(NotificationsScreenArgs.USER_ID) { NavType.IntType },
        )
    ) { NotificationsScreen(navController) }
}

class NotificationsScreenArgs(savedStateHandle: SavedStateHandle) {
    val userId: String = checkNotNull(savedStateHandle[USER_ID])

    companion object {
        const val USER_ID = "userId"
    }

}