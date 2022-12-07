package com.octopus.socialnetwork.ui.util

sealed class Screen(val route: String) {
    object HomeScreen : Screen("homeScreen")
    object PostScreen : Screen("postScreen")
    object ChatScreen : Screen("chat")
    object ProfileScreen : Screen("profile")
}
