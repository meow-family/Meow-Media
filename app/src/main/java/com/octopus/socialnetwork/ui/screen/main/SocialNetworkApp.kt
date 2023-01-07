package com.octopus.socialnetwork.ui.screen.main

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.octopus.socialnetwork.ui.composable.Loading
import com.octopus.socialnetwork.ui.navigation.RootNavigationGraph
import com.octopus.socialnetwork.ui.theme.SocialNetworkTheme


@Composable
fun SocialNetworkApp() {
    SocialNetworkTheme {
        val navController = rememberNavController()
        val viewModel: MainViewModel = hiltViewModel()
        val appState = viewModel.appState.collectAsState()

        if (appState.value.isLoading) {
            Loading()
        } else {
            Log.i("LOGOUT", "appState is ${appState.value}")
            RootNavigationGraph(navController, isLoggedOut = appState.value.isLoggedIn.not())
        }
    }
}

