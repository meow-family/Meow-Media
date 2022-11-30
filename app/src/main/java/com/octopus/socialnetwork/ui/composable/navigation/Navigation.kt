package com.octopus.socialnetwork.ui.composable.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.octopus.socialnetwork.ui.screen.home.HomeScreen
import com.octopus.socialnetwork.ui.screen.login.LoginScreen
import com.octopus.socialnetwork.ui.screen.onboarding.OnBoardingScreen
import com.octopus.socialnetwork.ui.screen.post.PostScreen
import com.octopus.socialnetwork.ui.screen.register.RegisterScreen
import com.octopus.socialnetwork.ui.utils.NavigationItems

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavigationItems.HomeScreen.route) {
        composable(NavigationItems.HomeScreen.route) {
            RegisterScreen()
        }
        composable(NavigationItems.SearchScreen.route) {
            LoginScreen()
        }
        composable(NavigationItems.ChatScreen.route) {
            OnBoardingScreen()
        }
        composable(NavigationItems.ProfileScreen.route) {
            PostScreen()
        }
    }
}