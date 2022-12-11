package com.octopus.socialnetwork.ui.screen.main

import androidx.compose.runtime.Composable
import com.octopus.socialnetwork.ui.screen.edit_profile.EditProfileScreen
import com.octopus.socialnetwork.ui.theme.SocialNetworkTheme


@Composable
fun SocialNetworkApp() {
    SocialNetworkTheme {
//        val navController = rememberNavController()
//        RootNavigationGraph(navController)
       EditProfileScreen()
    }
}

