package com.octopus.socialnetwork

sealed class Screen(val route: String) {
    object HomeScreen : Screen("home")
    object SearchScreen : Screen("search")
    object ChatScreen : Screen("chat")
    object ProfileScreen : Screen("profile")
}
