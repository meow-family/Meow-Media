package com.octopus.socialnetwork.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.octopus.socialnetwork.ui.screen.comments.commentsRoute
import com.octopus.socialnetwork.ui.screen.group.groupRoute
import com.octopus.socialnetwork.ui.screen.home.homeRoute
import com.octopus.socialnetwork.ui.screen.login.loginRoute
import com.octopus.socialnetwork.ui.screen.message_screen.messageRoute
import com.octopus.socialnetwork.ui.screen.notifications.notificationsRoute
import com.octopus.socialnetwork.ui.screen.profile.profileRoute

@Composable
fun MainNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = MainRoute.Home,
        route = Graph.MAIN
    ) {
        homeRoute(navController)
        groupRoute(navController)
        messageRoute(navController)
        profileRoute(navController)
        detailsNavigationGraph(navController)
        notificationsRoute(navController)
        commentsRoute(navController)
    }
}

object MainRoute {
    const val Home = "home"
    const val Group = "group"
    const val Chat = "Chat"
    const val Profile = "profile"
}
