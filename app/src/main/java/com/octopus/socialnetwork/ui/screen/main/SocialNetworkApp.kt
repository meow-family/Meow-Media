package com.octopus.socialnetwork.ui.screen.main

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.octopus.socialnetwork.ui.navigation.RootNavigationGraph
import com.octopus.socialnetwork.ui.screen.chat.ChatScreen
import com.octopus.socialnetwork.ui.screen.createPost.CreatePost
import com.octopus.socialnetwork.ui.screen.message_screen.MessageScreen
import com.octopus.socialnetwork.ui.theme.SocialNetworkTheme


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SocialNetworkApp() {
    SocialNetworkTheme {
        val navController = rememberNavController()
        RootNavigationGraph(navController)
        //CreatePost(navController = navController)
    }
}

