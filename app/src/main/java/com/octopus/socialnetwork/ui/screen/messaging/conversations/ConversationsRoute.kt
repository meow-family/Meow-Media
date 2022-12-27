package com.octopus.socialnetwork.ui.screen.messaging.conversations

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.octopus.socialnetwork.ui.navigation.MainRoute

private const val ROUTE = MainRoute.Conversations


fun NavController.navigateToConversations() {
    navigate(ROUTE)
}

fun NavGraphBuilder.conversationsRoute(navController: NavController) {
    composable(ROUTE) {
        ConversationsScreen(navController)
    }
}