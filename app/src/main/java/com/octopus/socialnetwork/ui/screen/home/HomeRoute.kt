package com.octopus.socialnetwork.ui.screen.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


private const val ROUTE = "home"
fun NavController.navigateToHomeScreen(){
    popBackStack()
}
fun NavGraphBuilder.homeRoute(navController: NavController) {
    composable(ROUTE) { HomeScreen(navController) }
}
