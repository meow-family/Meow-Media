package com.octopus.socialnetwork.ui.screen.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.navigationBarsPadding
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
import com.google.accompanist.pager.ExperimentalPagerApi
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.SocialNetworkApplication.Companion.userId
import com.octopus.socialnetwork.ui.composable.buttom_navigation_bar.BottomNavItem
import com.octopus.socialnetwork.ui.composable.buttom_navigation_bar.BottomNavigation
import com.octopus.socialnetwork.ui.composable.buttom_navigation_bar.FloatingActionButton
import com.octopus.socialnetwork.ui.navigation.MainNavigationGraph
import com.octopus.socialnetwork.ui.navigation.MainRoute
import com.octopus.socialnetwork.ui.theme.SocialNetworkTheme


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
                        route = MainRoute.Group,
                        icon = painterResource(R.drawable.group),
                    ),
                    BottomNavItem(
                        name = stringResource(R.string.chat),
                        route = MainRoute.Chat,
                        icon = painterResource(R.drawable.chat),
                    ),
                    BottomNavItem(
                        name = stringResource(R.string.profile),
                        route = MainRoute.Profile + "/$userId",
                        icon = painterResource(R.drawable.profile),
                    ),
                ),
                navController = navController,
                onItemClick = {
                    navController.navigate(it.route)
                }
            )

        },
        floatingActionButton = {
            FloatingActionButton {}
        }
    ) {

        MainNavigationGraph(navController,rootNavController)

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