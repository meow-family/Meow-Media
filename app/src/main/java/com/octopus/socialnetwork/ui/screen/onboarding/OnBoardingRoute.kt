package com.octopus.socialnetwork.ui.screen.onboarding

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val ROUTE_ON_BOARDING = "onBoarding"

fun NavController.navigateToOnBoarding() {
    navigate(ROUTE_ON_BOARDING)
}

fun NavGraphBuilder.onBoardingRoute(navController: NavController) {
    composable(ROUTE_ON_BOARDING) {
        OnBoardingScreen(navController)
    }
}