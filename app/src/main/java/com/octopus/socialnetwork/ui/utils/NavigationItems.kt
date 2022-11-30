package com.octopus.socialnetwork.ui.utils

sealed class NavigationItems(val route: String) {
    object HomeScreen : NavigationItems("home")
    object SearchScreen : NavigationItems("search")
    object ChatScreen : NavigationItems("chat")
    object ProfileScreen : NavigationItems("profile")
}
