package com.octopus.socialnetwork.ui.navigate

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun SocialNetworkNavGraph(navController: NavHostController){
    NavHost(navController = navController, startDestination = "profileScreen"){
        homeRoute(navController)
        profileRoute(navController)
        postRoute(navController)
    }

}