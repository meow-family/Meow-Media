package com.octopus.socialnetwork.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.octopus.socialnetwork.ui.screen.comments.commentsRoute
import com.octopus.socialnetwork.ui.screen.group.groupRoute
import com.octopus.socialnetwork.ui.screen.home.homeRoute
import com.octopus.socialnetwork.ui.screen.message_screen.messageRoute
import com.octopus.socialnetwork.ui.screen.profile.profileRoute

@Composable
fun MainNavigationGraph(navController: NavHostController, rootNavController: NavController) {
    NavHost(
        navController = navController,
        startDestination = MainRoute.Home,
        route = Graph.MAIN
    ) {
        homeRoute(rootNavController)
        groupRoute(rootNavController)
        messageRoute(rootNavController)
        profileRoute(rootNavController)
        commentsRoute(navController)
    }
}

object MainRoute {
    const val Home = "home"
    const val Group = "group"
    const val Chat = "Chat"
    const val Profile = "profile"
}
