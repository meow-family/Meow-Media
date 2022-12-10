package com.octopus.socialnetwork.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import com.octopus.socialnetwork.ui.screen.notifications.notificationRoute
import com.octopus.socialnetwork.ui.screen.post.postRoute


fun NavGraphBuilder.detailsNavigationGraph(navController: NavHostController) {
    navigation(
        startDestination = DetailsRoute.Post,
        route = Graph.DETAILS
    ) {
        notificationRoute(navController)
        postRoute(navController)

    }
}


object DetailsRoute {
    const val Post = "post"
    const val Notification = "notification"
}