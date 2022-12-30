package com.octopus.socialnetwork.ui.screen.login

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.octopus.socialnetwork.ui.screen.login.state.LoginViewModel

private const val ROUTE = "login"

fun NavController.navigateToLogin() {
    navigate(ROUTE)
}

fun NavGraphBuilder.loginRoute(navController: NavController, loginViewModel: LoginViewModel) {
    composable(ROUTE) {
        LoginScreen(navController, loginViewModel)
    }
}