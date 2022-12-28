package com.octopus.socialnetwork.ui.screen.edit_profile

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.octopus.socialnetwork.ui.navigation.DetailsRoute

private const val ROUTE = DetailsRoute.EditeProfile

fun NavController.navigateToEditeProfileRoute(userId: Int) {
    navigate("${ROUTE}/$userId") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.editeProfileRouteRoute(navController: NavController) {
    composable(
        route = "${ROUTE}/{${EditProfileScreenArgs.USER_ID}}",
        arguments = listOf(
            navArgument(name = EditProfileScreenArgs.USER_ID) { NavType.StringType }
        )
    ) { EditProfileScreen(navController) }
}

class EditProfileScreenArgs(savedStateHandle: SavedStateHandle) {

    val userId: String? = savedStateHandle[USER_ID]

    companion object {
        const val USER_ID = "userId"
    }
}