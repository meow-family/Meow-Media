package com.octopus.socialnetwork.ui.screen.profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "profile"

fun NavGraphBuilder.profileRoute(navController: NavController) {
    composable(ROUTE) {
        ProfileScreen(navController)
    }
}