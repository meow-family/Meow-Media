package com.octopus.socialnetwork.ui.navigation


import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import com.octopus.socialnetwork.ui.screen.login.loginRoute
import com.octopus.socialnetwork.ui.screen.onboarding.onBoardingRoute
import com.octopus.socialnetwork.ui.screen.register.registerRoute

fun NavGraphBuilder.authNavigationGraph(navController: NavHostController) {

    navigation(
        startDestination = AuthenticationRoute.OnBoarding,
        route = Graph.AUTH
    ) {
        onBoardingRoute(navController)
        loginRoute(navController)
        registerRoute(navController)

    }
}


object AuthenticationRoute {
    const val OnBoarding = "onBoarding"
    const val Login = "login"
    const val Register = "register"
}
