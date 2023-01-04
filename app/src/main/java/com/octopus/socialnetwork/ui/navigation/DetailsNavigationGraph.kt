package com.octopus.socialnetwork.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import com.octopus.socialnetwork.ui.screen.comments.commentsRoute
import com.octopus.socialnetwork.ui.screen.create_post.createPostRoute
import com.octopus.socialnetwork.ui.screen.edit_profile.editeProfileRouteRoute
import com.octopus.socialnetwork.ui.screen.friend_request.friendRequestsRoute
import com.octopus.socialnetwork.ui.screen.messaging.chat.chatRoute
import com.octopus.socialnetwork.ui.screen.notifications.ROUTE_NOTIFICATION
import com.octopus.socialnetwork.ui.screen.notifications.notificationsRoute
import com.octopus.socialnetwork.ui.screen.post.postRoute
import com.octopus.socialnetwork.ui.screen.profile.userProfileRoute


fun NavGraphBuilder.detailsNavigationGraph(navController: NavHostController) {
    navigation(
        startDestination = ROUTE_NOTIFICATION,
        route = Graph.DETAILS
    ) {
        postRoute(navController)
        commentsRoute(navController)
        notificationsRoute(navController)
        chatRoute(navController)
        editeProfileRouteRoute(navController)
        userProfileRoute(navController)
        friendRequestsRoute(navController)
        createPostRoute(navController)
    }
}
