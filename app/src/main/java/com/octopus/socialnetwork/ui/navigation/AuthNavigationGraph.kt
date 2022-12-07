package com.octopus.socialnetwork.ui.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.octopus.socialnetwork.ui.screen.login.loginRoute
import com.octopus.socialnetwork.ui.screen.onboarding.onBoardingRoute
import com.octopus.socialnetwork.ui.screen.register.registerRoute

@Composable
fun AuthNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "on_boarding",
        route = Graph.AUTH
    ) {
        onBoardingRoute(navController)
        loginRoute(navController)
        registerRoute(navController)

    }
}
