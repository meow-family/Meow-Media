package com.octopus.socialnetwork.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import com.octopus.socialnetwork.ui.screen.post.postRoute


fun NavGraphBuilder.detailsNavigationGraph(navController: NavHostController) {
    navigation(
        startDestination = DetailsRoute.Post,
        route = Graph.DETAILS
    ) {
        postRoute(navController)

    }
}


object DetailsRoute {
    const val Post = "post"
}