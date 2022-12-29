package com.octopus.socialnetwork.ui.screen.messaging.chat

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

private const val ROUTE = "chat"

fun NavController.navigateToChat(userId: Int){
    navigate("$ROUTE/$userId")
}
fun NavGraphBuilder.chatRoute(navController: NavController) {
    composable(
        "$ROUTE/{${ChatScreenArgs.USER_ID}}",
        arguments = listOf(
            navArgument(ChatScreenArgs.USER_ID) { NavType.IntType },
        )
    ) {
        ChatScreen(navController)
    }
}

class ChatScreenArgs(savedStateHandle: SavedStateHandle) {
    val friendId: String = checkNotNull(savedStateHandle[USER_ID])

    companion object {
        const val USER_ID = "friendId"
    }

}