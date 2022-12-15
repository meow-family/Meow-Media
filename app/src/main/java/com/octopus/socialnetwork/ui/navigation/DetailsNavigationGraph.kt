package com.octopus.socialnetwork.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import com.octopus.socialnetwork.ui.screen.comments.commentsRoute
import com.octopus.socialnetwork.ui.screen.edit_profile.editeProfileRouteRoute
import com.octopus.socialnetwork.ui.screen.friend_request.friendRequestsRoute
import com.octopus.socialnetwork.ui.screen.notifications.notificationsRoute
import com.octopus.socialnetwork.ui.screen.post.postRoute
import com.octopus.socialnetwork.ui.screen.profile.userProfileRoute


fun NavGraphBuilder.detailsNavigationGraph(navController: NavHostController) {
    navigation(
        startDestination = DetailsRoute.Post,
        route = Graph.DETAILS
    ) {
        postRoute(navController)
        commentsRoute(navController)
        notificationsRoute(navController)
        editeProfileRouteRoute(navController)
        userProfileRoute(navController)
        friendRequestsRoute(navController)
    }
}


object DetailsRoute {
    const val Post = "post"
    const val Comments = "comments"
    const val EditeProfile = "edite_profile"
    const val Notifications = "notifications"
}