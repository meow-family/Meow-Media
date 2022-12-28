package com.octopus.socialnetwork.ui.screen.post

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.octopus.socialnetwork.ui.navigation.DetailsRoute


private const val ROUTE = DetailsRoute.Post
fun NavController.navigateToPostScreen(postId: Int,postOwner: Int){
    navigate("$ROUTE/$postId/$postOwner"
    ) {
        launchSingleTop = true
    }
}
fun NavGraphBuilder.postRoute(navController: NavController) {
    composable("$ROUTE/{${PostScreenArgs.POST_ID}}/{${PostScreenArgs.POST_OWNER_ID}}",
        arguments = listOf(
            navArgument(PostScreenArgs.POST_ID) { NavType.IntType },
            navArgument(PostScreenArgs.POST_OWNER_ID) { NavType.IntType }
        )

    ) { PostScreen(navController) }
}

class PostScreenArgs(savedStateHandle: SavedStateHandle) {
    val postId: String = checkNotNull(savedStateHandle[POST_ID])
    val postOwnerId: String = checkNotNull(savedStateHandle[POST_OWNER_ID])

    companion object {
        const val POST_ID = "postId"
        const val POST_OWNER_ID = "postOwnerId"
    }

}