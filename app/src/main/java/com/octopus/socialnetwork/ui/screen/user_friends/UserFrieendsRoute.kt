package com.octopus.socialnetwork.ui.screen.user_friends

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.octopus.socialnetwork.ui.navigation.MainRoute

private const val ROUTE = "friendsScreen"
fun NavController.navigateToProfileScreen() {
    popBackStack()
}

fun NavGraphBuilder.userFriendsRoute(navController: NavController) {
    composable(ROUTE) { UserFriendsScreen(navController) }
}
