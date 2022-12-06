package com.octopus.socialnetwork.ui.navigate

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.octopus.socialnetwork.ui.screen.home.HomeScreen


private const val ROUTE = "homeScreen"

fun NavController.navigateToHomeScreen(){
    popBackStack()
}

fun NavGraphBuilder.homeRoute(navController: NavController) {
    composable(route = ROUTE) { HomeScreen(navController) }
}
