package com.octopus.socialnetwork.ui.screen.onboarding

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "on_boarding"

fun NavGraphBuilder.onBoardingRoute(navController: NavController) {
    composable(ROUTE) { OnBoardingScreen(navController) }
}