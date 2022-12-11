package com.octopus.socialnetwork.ui.screen.main

import androidx.compose.runtime.Composable
import com.octopus.socialnetwork.ui.screen.chat.ChatScreen
import com.octopus.socialnetwork.ui.theme.SocialNetworkTheme


@Composable
fun SocialNetworkApp() {
    SocialNetworkTheme {
        ChatScreen()
    }
}

