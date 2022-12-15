package com.octopus.socialnetwork.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.octopus.socialnetwork.ui.screen.home.homeRoute
import com.octopus.socialnetwork.ui.screen.message_screen.messageRoute
import com.octopus.socialnetwork.ui.screen.profile.myProfileRoute
import com.octopus.socialnetwork.ui.screen.search.searchRoute

@Composable
fun MainNavigationGraph(navController: NavHostController, rootNavController: NavController) {
    NavHost(
        navController = navController,
        startDestination = MainRoute.Home,
        route = Graph.MAIN
    ) {
        homeRoute(rootNavController)
        searchRoute(rootNavController)
        messageRoute(rootNavController)
        myProfileRoute(rootNavController)
    }
}

object MainRoute {
    const val Home = "home"
    const val Search = "search"
    const val Chat = "messages"
    const val Profile = "profile"
}
