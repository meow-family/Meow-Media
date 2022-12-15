package com.octopus.socialnetwork.ui.screen.main

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.octopus.socialnetwork.ui.navigation.RootNavigationGraph
import com.octopus.socialnetwork.ui.screen.chat.ChatScreen
import com.octopus.socialnetwork.ui.screen.message_screen.MessageScreen
import com.octopus.socialnetwork.ui.theme.SocialNetworkTheme


@Composable
fun SocialNetworkApp() {
    SocialNetworkTheme {
        val navController = rememberNavController()
      //  RootNavigationGraph(navController)
        ChatScreen(navController)
    }
}

