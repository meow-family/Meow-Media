package com.octopus.socialnetwork.ui.screen.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.octopus.socialnetwork.ui.navigation.MainRoute


private const val ROUTE = MainRoute.Home
fun NavController.navigateToHomeScreen(){
    popBackStack()
}
fun NavGraphBuilder.homeRoute(navController: NavController) {
    composable(ROUTE) { HomeScreen(navController) }
}
