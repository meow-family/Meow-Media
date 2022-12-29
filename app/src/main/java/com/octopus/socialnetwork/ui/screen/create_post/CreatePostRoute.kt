package com.octopus.socialnetwork.ui.screen.create_post

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "create_post"

fun NavController.navigateToCreatePostRoute() {
    navigate(ROUTE)
}

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.createPostRoute(navController: NavController) {
    composable(ROUTE) {
        CreatePostScreen(navController)
    }
}