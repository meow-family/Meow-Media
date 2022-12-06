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
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.SocialNetworkNavGraph
import com.octopus.socialnetwork.ui.composable.navigation.BottomNavItem
import com.octopus.socialnetwork.ui.composable.navigation.BottomNavigation
import com.octopus.socialnetwork.ui.composable.navigation.FloatingActionButton
import com.octopus.socialnetwork.ui.theme.SocialNetworkTheme


@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "SuspiciousIndentation")
@Composable
@ExperimentalPagerApi
@ExperimentalMaterialApi
fun SocialNetworkApp() {
    SocialNetworkTheme {
        val navController = rememberNavController()

        Scaffold(
            modifier = Modifier.navigationBarsPadding(),
            isFloatingActionButtonDocked = true,
            floatingActionButtonPosition = FabPosition.Center,
            bottomBar = {
                BottomNavigation(
                     listOf(
                         BottomNavItem(
                             name = stringResource(R.string.home),
                             route = "home",
                             icon = painterResource(R.drawable.home),
                         ),
                         BottomNavItem(
                             name = stringResource(R.string.search),
                             route = "on_boarding",
                             icon = painterResource(R.drawable.search),
                         ),
                        BottomNavItem(
                            name = stringResource(R.string.chat),
                            route = "login",
                            icon = painterResource(R.drawable.chat),
                        ),
                        BottomNavItem(
                            name = stringResource(R.string.profile),
                            route = "profile",
                            icon = painterResource(R.drawable.profile),
                        ),
                    ),
                    navController = navController,
                    onItemClick = {
                        navController.navigate(it.route)
                    })

            },
            floatingActionButton = {
                FloatingActionButton {}
            }
        ) {

            SocialNetworkNavGraph(navController = navController)

        }

    }

}


@Preview
@Composable
@ExperimentalPagerApi
@ExperimentalMaterialApi
fun SocialNetworkAppPreview() {
    SocialNetworkTheme {

        SocialNetworkApp()

    }
}