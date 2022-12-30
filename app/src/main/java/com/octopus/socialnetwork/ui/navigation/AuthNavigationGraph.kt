package com.octopus.socialnetwork.ui.navigation


import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import com.octopus.socialnetwork.ui.screen.login.loginRoute
import com.octopus.socialnetwork.ui.screen.login.state.LoginViewModel
import com.octopus.socialnetwork.ui.screen.onboarding.ROUTE_ON_BOARDING
import com.octopus.socialnetwork.ui.screen.onboarding.onBoardingRoute
import com.octopus.socialnetwork.ui.screen.register.RegisterViewModel
import com.octopus.socialnetwork.ui.screen.register.registerRoute


fun NavGraphBuilder.authNavigationGraph(
    navController: NavHostController,
    registerViewModel: RegisterViewModel,
    loginViewModel: LoginViewModel
) {
    navigation(
        startDestination = ROUTE_ON_BOARDING,
        route = Graph.AUTH
    ) {
        onBoardingRoute(navController)
        loginRoute(navController, loginViewModel)
        registerRoute(navController, registerViewModel)
    }
}
