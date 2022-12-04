package com.octopus.socialnetwork.ui.screen.post

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument


private const val ROUTE = "postScreen"
fun NavGraphBuilder.postRoute(navController: NavController) {
    composable("$ROUTE/{${PostScreenArgs.POST_ID}}",
        arguments = listOf(
            navArgument(PostScreenArgs.POST_ID) { NavType.IntType },
            navArgument(PostScreenArgs.POST_OWNERID) { NavType.IntType }

        )

    ) { PostScreen(navController) }
}

class PostScreenArgs(savedStateHandle: SavedStateHandle) {
    val postId: String = checkNotNull(savedStateHandle[POST_ID])
    val postOwnerId: String = checkNotNull(savedStateHandle[POST_OWNERID])

    companion object {
        const val POST_ID = "postId"
        const val POST_OWNERID = "postOwnerId"
    }
}