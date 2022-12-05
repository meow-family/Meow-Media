package com.octopus.socialnetwork.ui.screen.login

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "login"

fun NavGraphBuilder.loginRoute(navController: NavController) {
    composable(ROUTE) {
        LoginScreen(navController)
    }
}