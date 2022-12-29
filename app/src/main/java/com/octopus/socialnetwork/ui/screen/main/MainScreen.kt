package com.octopus.socialnetwork.ui.screen.main

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.*
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.composable.buttom_navigation_bar.BottomNavItem
import com.octopus.socialnetwork.ui.composable.buttom_navigation_bar.BottomNavigation
import com.octopus.socialnetwork.ui.composable.buttom_navigation_bar.CustomFloatingActionButton
import com.octopus.socialnetwork.ui.composable.coloredShadow
import com.octopus.socialnetwork.ui.navigation.MainNavigationGraph
import com.octopus.socialnetwork.ui.navigation.MainRoute
import com.octopus.socialnetwork.ui.screen.create_post.navigateToCreatePostRoute
import com.octopus.socialnetwork.ui.theme.SocialNetworkTheme

@Composable
@ExperimentalPagerApi
@ExperimentalMaterialApi
@OptIn(ExperimentalLayoutApi::class)
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
                        route = MainRoute.Conversations,
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
                        Log.i("NAVIGATION","---- current backstack ${navController.currentBackStack.value.size} ---")
                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )

        },
        floatingActionButton = {
            CustomFloatingActionButton(
                modifier = Modifier.coloredShadow(
                    Color.Gray,
                    alpha = 0.1F,
                    offsetY = (-2).dp,
                    shadowRadius = 2.dp,
                    borderRadius = 65.dp
                ),
            ) {
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

@Preview(showSystemUi = true)
@Composable
@ExperimentalPagerApi
@ExperimentalMaterialApi
private fun SocialNetworkAppPreview() {
    SocialNetworkTheme {
        MainScreen(rememberNavController(), rememberNavController())
    }
}