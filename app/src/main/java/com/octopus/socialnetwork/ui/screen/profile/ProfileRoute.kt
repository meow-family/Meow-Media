package com.octopus.socialnetwork.ui.screen.profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.octopus.socialnetwork.ui.navigation.MainRoute


private const val ROUTE = MainRoute.Profile


fun NavController.navigateToProfileScreen() {
    navigate(ROUTE)
}

fun NavGraphBuilder.profileRoute(navController: NavController) {
    composable(ROUTE)
    { ProfileScreen(navController) }
}



//private const val ROUTE = MainRoute.Profile
//
//
//fun NavController.navigateToProfileScreen(userId: Int) {
//    navigate("$ROUTE/$userId")
//}
//
//fun NavGraphBuilder.profileRoute(navController: NavController) {
//    composable(
//
//        route = "$ROUTE/{${ProfileScreenArgs.USER_ID}}",
//        arguments = listOf(
//            navArgument(name = ProfileScreenArgs.USER_ID) { NavType.IntType }
//        )
//    ) { ProfileScreen(navController) }
//}
//
//class ProfileScreenArgs(savedStateHandle: SavedStateHandle) {
//    val userIdVisitor: Int = checkNotNull(savedStateHandle[USER_ID]).toString().toInt()
//
//    companion object {
//        const val USER_ID = "userId"
//    }
//}