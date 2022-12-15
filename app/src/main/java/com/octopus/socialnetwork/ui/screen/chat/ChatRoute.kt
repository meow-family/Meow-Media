package com.octopus.socialnetwork.ui.screen.chat

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.octopus.socialnetwork.ui.navigation.AuthenticationRoute
import com.octopus.socialnetwork.ui.navigation.DetailsRoute
import com.octopus.socialnetwork.ui.navigation.MainRoute
import com.octopus.socialnetwork.ui.screen.post.PostScreen

private const val ROUTE = DetailsRoute.Chat


fun NavController.navigateToChat() {
    navigate(ROUTE)
}

fun NavController.navigateToChat(userId: Int){
    navigate("${ROUTE}/$userId")
}
fun NavGraphBuilder.chatRoute(navController: NavController) {
    composable("${ROUTE}/{${ChatScreenArgs.USER_ID}}",
        arguments = listOf(
            navArgument(ChatScreenArgs.USER_ID) { NavType.IntType },
        )

    ) { ChatScreen(navController) }
}

class ChatScreenArgs(savedStateHandle: SavedStateHandle) {
    val userId: String = checkNotNull(savedStateHandle[USER_ID])


    companion object {
        const val USER_ID = "otherUserId"
    }

}