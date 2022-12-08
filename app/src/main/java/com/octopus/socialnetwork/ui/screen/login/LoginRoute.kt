package com.octopus.socialnetwork.ui.screen.login

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.octopus.socialnetwork.ui.navigation.AuthenticationRoute

private const val ROUTE = AuthenticationRoute.Login


fun NavController.navigateToLogin() {
    navigate(ROUTE)
}

fun NavGraphBuilder.loginRoute(navController: NavController) {
    composable(ROUTE) {
        LoginScreen(navController)
    }
}