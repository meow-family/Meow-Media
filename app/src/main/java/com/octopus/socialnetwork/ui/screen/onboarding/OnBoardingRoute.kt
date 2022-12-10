package com.octopus.socialnetwork.ui.screen.onboarding

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.octopus.socialnetwork.ui.navigation.AuthenticationRoute

private const val ROUTE = AuthenticationRoute.OnBoarding

fun NavGraphBuilder.onBoardingRoute(navController: NavController) {
    composable(ROUTE) { OnBoardingScreen(navController) }
}