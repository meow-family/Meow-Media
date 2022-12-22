package com.octopus.socialnetwork.ui.screen.edit_profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.octopus.socialnetwork.ui.navigation.DetailsRoute


private const val ROUTE = DetailsRoute.EditeProfile

fun NavController.navigateToEditProfileRoute() {
    navigate(ROUTE)
}

fun NavGraphBuilder.editeProfileRouteRoute(navController: NavController) {
    composable(ROUTE) { EditProfileScreen(navController) }
}