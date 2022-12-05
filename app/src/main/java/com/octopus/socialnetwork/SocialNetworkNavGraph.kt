package com.octopus.socialnetwork

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.octopus.socialnetwork.ui.screen.home.homeRoute
import com.octopus.socialnetwork.ui.screen.post.postRoute

@Composable
fun SocialNetworkNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "homeScreen") {
       // composable("homeScreen") { HomeScreen(navController) }
        homeRoute(navController)
        postRoute(navController)

    }
}