package com.octopus.socialnetwork.ui.screen.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.consumedWindowInsets
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.navOptions
import com.google.accompanist.pager.ExperimentalPagerApi
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.composable.buttom_navigation_bar.BottomNavItem
import com.octopus.socialnetwork.ui.composable.buttom_navigation_bar.BottomNavigation
import com.octopus.socialnetwork.ui.composable.buttom_navigation_bar.FloatingActionButton
import com.octopus.socialnetwork.ui.navigation.MainNavigationGraph
import com.octopus.socialnetwork.ui.navigation.MainRoute
import com.octopus.socialnetwork.ui.screen.create_post.navigateToCreatePostRoute
import com.octopus.socialnetwork.ui.theme.SocialNetworkTheme


@OptIn(ExperimentalLayoutApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "SuspiciousIndentation")
@Composable
@ExperimentalPagerApi
@ExperimentalMaterialApi
fun MainScreen(navController: NavHostController, rootNavController: NavController) {

    Scaffold(
        modifier = Modifier.navigationBarsPadding(),
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = {
            BottomNavigation(
                listOf(
                    BottomNavItem(
                        name = stringResource(R.string.home),
                        route = MainRoute.Home,
                        icon = painterResource(R.drawable.home),
                    ),
                    BottomNavItem(
                        name = stringResource(R.string.search),
                        route = MainRoute.Search,
                        icon = painterResource(R.drawable.search),
                    ),
                    BottomNavItem(
                        name = stringResource(R.string.chat),
                        route = MainRoute.Messages,
                        icon = painterResource(R.drawable.chat),
                    ),
                    BottomNavItem(
                        name = stringResource(R.string.profile),
                        route = MainRoute.Profile,
                        icon = painterResource(R.drawable.profile),
                    ),
                ),
                navController = navController,
                onItemClick = {
                    navController.navigate(it.route) {
                        navOptions { launchSingleTop = true }
                    }
                }
            )

        },
        floatingActionButton = {
            FloatingActionButton {
                rootNavController.navigateToCreatePostRoute()
            }
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .consumedWindowInsets(innerPadding)
                .padding(innerPadding)
                .imePadding()
        ) {
            MainNavigationGraph(navController, rootNavController)
        }

    }
}

@Preview
@Composable
@ExperimentalPagerApi
@ExperimentalMaterialApi
fun SocialNetworkAppPreview() {
    SocialNetworkTheme {

//        SocialNetworkApp()

    }
}