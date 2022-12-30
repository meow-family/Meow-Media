package com.octopus.socialnetwork.ui.screen.profile

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.octopus.socialnetwork.ui.navigation.MainRoute


private const val ROUTE = MainRoute.Profile

fun NavGraphBuilder.myProfileRoute(navController: NavController) {
    composable(ROUTE) { ProfileScreen(navController) }
}


fun NavController.navigateToUserProfileScreen(userId: Int) {
    navigate("$ROUTE/$userId")
}

fun NavGraphBuilder.userProfileRoute(navController: NavController) {
    composable(
        route = "$ROUTE/{${ProfileScreenArgs.USER_ID}}",
        arguments = listOf(
            navArgument(name = ProfileScreenArgs.USER_ID) { NavType.StringType }
        )
    ) { ProfileScreen(navController) }
}

class ProfileScreenArgs(savedStateHandle: SavedStateHandle) {
    val visitedUserId: String? = savedStateHandle[USER_ID]
    companion object {
        const val USER_ID = "userId"
    }
}