package com.octopus.socialnetwork.ui.navigate

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.octopus.socialnetwork.ui.screen.post.PostScreen


private const val ROUTE = "postScreen"

fun NavController.navigateToPostScreen(postId: Int, postOwnerId: Int){
    navigate("$ROUTE/$postId/$postOwnerId")
}

fun NavGraphBuilder.postRoute(navController: NavController) {
    composable(
        route = "$ROUTE/{${PostScreenArgs.POST_ID}}/{${PostScreenArgs.POST_OWNER_ID}}",
        arguments = listOf(
            navArgument(name = PostScreenArgs.POST_ID){ NavType.IntType },
            navArgument(name = PostScreenArgs.POST_OWNER_ID){ NavType.IntType },
        )
    ){ PostScreen(navController) }
}

class PostScreenArgs(savedStateHandle: SavedStateHandle) {
    val postId: String = checkNotNull(savedStateHandle[POST_ID])
    val postOwnerId: String = checkNotNull(savedStateHandle[POST_OWNER_ID])

    companion object{
        const val POST_ID = "postId"
        const val POST_OWNER_ID = "postOwnerId"
    }
}