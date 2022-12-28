package com.octopus.socialnetwork.ui.navigation


import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.octopus.socialnetwork.SocialNetworkApplication
import com.octopus.socialnetwork.ui.screen.main.mainRoute

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RootNavigationGraph(navController: NavHostController,isLoggedOut: Boolean) {
    NavHost(
        navController = navController,
        startDestination = if (isLoggedOut) Graph.AUTH else Graph.MAIN,
        route = Graph.ROOT
    ) {
        authNavigationGraph(navController)
        mainRoute(navController)
        detailsNavigationGraph(navController)
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val AUTH = "auth_graph"
    const val MAIN = "main_graph"
    const val DETAILS = "details_graph"
}