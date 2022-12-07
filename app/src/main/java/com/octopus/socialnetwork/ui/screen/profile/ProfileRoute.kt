package com.octopus.socialnetwork.ui.screen.profile

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.octopus.socialnetwork.ui.navigation.MainRoute

private const val ROUTE = MainRoute.Profile


fun NavController.navigateToProfileScreen(userId: Int){
    navigate("$ROUTE/$userId")
}

fun NavGraphBuilder.profileRoute(navController: NavController) {
    composable(
        route = "$ROUTE" //{${ProfileScreenArgs.USER_ID}}
//        arguments = listOf(
//            navArgument(name = ProfileScreenArgs.USER_ID){ NavType.IntType }
//        )
    ){ ProfileScreen(navController) }
}

class ProfileScreenArgs(savedStateHandle: SavedStateHandle) {
    val userId: String = checkNotNull(savedStateHandle[USER_ID])

    companion object{
        const val USER_ID = "userId"
    }
}