package com.octopus.socialnetwork

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.octopus.socialnetwork.ui.screen.home.homeRoute
import com.octopus.socialnetwork.ui.screen.login.loginRoute
import com.octopus.socialnetwork.ui.screen.onboarding.onBoardingRoute
import com.octopus.socialnetwork.ui.screen.profile.profileRoute

@Composable
fun SocialNetworkNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "homeScreen") {
        homeRoute(navController)
        onBoardingRoute(navController)
        loginRoute(navController)
        profileRoute(navController)
        postRoute(navController)
    }
}