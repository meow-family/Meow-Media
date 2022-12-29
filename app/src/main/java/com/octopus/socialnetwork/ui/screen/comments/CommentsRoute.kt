package com.octopus.socialnetwork.ui.screen.comments

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument


private const val ROUTE = "comments"
fun NavController.navigateToCommentsScreen(postId: Int, type: String){
    navigate("$ROUTE/$postId/$type")
}
fun NavGraphBuilder.commentsRoute(navController: NavController) {
    composable("$ROUTE/{${CommentsScreenArgs.POST_ID}}/{${CommentsScreenArgs.TYPE}}",
        arguments = listOf(
            navArgument(CommentsScreenArgs.POST_ID) { NavType.IntType },
            navArgument(CommentsScreenArgs.TYPE) { NavType.StringType },
        )
    ) { CommentsScreen(navController) }
}

class CommentsScreenArgs(savedStateHandle: SavedStateHandle) {
    val postId: String = checkNotNull(savedStateHandle[POST_ID])
    val type: String = checkNotNull(savedStateHandle[TYPE])

    companion object {
        const val POST_ID = "postId"
        const val TYPE = "type"
    }

}