package com.octopus.socialnetwork.ui.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.octopus.socialnetwork.ui.screen.home.homeRoute
import com.octopus.socialnetwork.ui.screen.login.loginRoute
import com.octopus.socialnetwork.ui.screen.onboarding.onBoardingRoute
import com.octopus.socialnetwork.ui.screen.post.postRoute
import com.octopus.socialnetwork.ui.screen.profile.profileRoute

@Composable
fun MainNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = MainRoute.Home,
        route = Graph.MAIN
    ) {
        homeRoute(navController)
        onBoardingRoute(navController)
        loginRoute(navController)
        profileRoute(navController)
        postRoute(navController)

    }
}

object MainRoute {
    const val Home = "home"
    const val Group = "group"
    const val Chat = "Chat"
    const val Profile = "profile"
}
