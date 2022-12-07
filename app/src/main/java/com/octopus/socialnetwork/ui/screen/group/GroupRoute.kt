package com.octopus.socialnetwork.ui.screen.group

import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.octopus.socialnetwork.ui.navigation.MainRoute
import com.octopus.socialnetwork.ui.screen.register.RegisterScreen

private const val ROUTE = MainRoute.Group


fun NavController.navigateToGroup() {
    navigate(ROUTE)
}

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class)
fun NavGraphBuilder.groupRoute(navController: NavController) {
    composable(ROUTE) {
        RegisterScreen(navController)
    }
}