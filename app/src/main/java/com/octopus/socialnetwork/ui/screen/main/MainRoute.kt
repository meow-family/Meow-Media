package com.octopus.socialnetwork.ui.screen.main

import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.octopus.socialnetwork.ui.navigation.Graph

private const val ROUTE = Graph.MAIN


fun NavController.navigateToMain() {
    navigate(ROUTE)
}

@ExperimentalMaterialApi
@OptIn(ExperimentalPagerApi::class)
fun NavGraphBuilder.mainRoute(navController: NavController) {

    composable(ROUTE) {
        MainScreen(
            navController = rememberNavController(),
            rootNavController = navController
        )
    }
}