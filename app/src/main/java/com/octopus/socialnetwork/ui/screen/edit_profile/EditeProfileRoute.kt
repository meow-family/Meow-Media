package com.octopus.socialnetwork.ui.screen.edit_profile

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

private const val ROUTE = "edite_profile"

fun NavController.navigateToEditeProfileRoute(userId: Int) {
    navigate("${ROUTE}/$userId")
}

fun NavGraphBuilder.editeProfileRouteRoute(navController: NavController) {
    composable(
        route = "${ROUTE}/{${EditProfileScreenArgs.USER_ID}}",
        arguments = listOf(
            navArgument(name = EditProfileScreenArgs.USER_ID) { NavType.StringType }
        )
    ) {
        EditProfileScreen(navController)
    }
}

class EditProfileScreenArgs(savedStateHandle: SavedStateHandle) {
    val userId: String? = savedStateHandle[USER_ID]

    companion object {
        const val USER_ID = "userId"
    }
}