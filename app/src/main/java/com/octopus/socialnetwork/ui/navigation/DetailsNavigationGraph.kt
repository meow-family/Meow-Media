package com.octopus.socialnetwork.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import com.octopus.socialnetwork.ui.screen.messaging.chat.chatRoute
import com.octopus.socialnetwork.ui.screen.comments.commentsRoute
import com.octopus.socialnetwork.ui.screen.create_post.createPostRoute
import com.octopus.socialnetwork.ui.screen.edit_profile.editeProfileRouteRoute
import com.octopus.socialnetwork.ui.screen.friend_request.friendRequestsRoute
import com.octopus.socialnetwork.ui.screen.notifications.notificationsRoute
import com.octopus.socialnetwork.ui.screen.post.postRoute
import com.octopus.socialnetwork.ui.screen.profile.userProfileRoute


@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.detailsNavigationGraph(navController: NavHostController) {
    navigation(
        startDestination = DetailsRoute.Post,
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


object DetailsRoute {
    const val Post = "post"
    const val Comments = "comments"
    const val EditeProfile = "edite_profile"
    const val Notifications = "notifications"
    const val Chat = "chat"
    const val CreatePost = "create_post"
}