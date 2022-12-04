package com.octopus.socialnetwork

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.octopus.socialnetwork.ui.screen.home.HomeScreen

@Composable
fun SocialNetworkNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "homeScreen") {
      //  composable("homeScreen") { HomeScreen() }

    }
}