package com.octopus.socialnetwork.ui.screen.create_post

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "create_post"

fun NavController.navigateToCreatePostRoute() {
    navigate(ROUTE) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.createPostRoute(navController: NavController) {
    composable(ROUTE) {
        CreatePostScreen(navController)
    }
}