package com.octopus.socialnetwork.ui.screen.register

import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.octopus.socialnetwork.ui.navigation.AuthenticationRoute

private const val ROUTE = AuthenticationRoute.Register


fun NavController.navigateToRegister() {
    navigate(ROUTE)
}

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class)
fun NavGraphBuilder.registerRoute(navController: NavController) {
    composable(ROUTE) {
        RegisterScreen(navController)
    }
}