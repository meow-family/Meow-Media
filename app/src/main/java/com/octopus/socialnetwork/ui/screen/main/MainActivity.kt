package com.octopus.socialnetwork.ui.screen.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.octopus.socialnetwork.ui.navigation.BottomNavItem
import com.octopus.socialnetwork.ui.navigation.BottomNavigation
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.theme.SocialNetworkTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalPagerApi
@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SocialNetworkTheme {
                val navController = rememberNavController()
                BottomNavigation(
                    items = listOf(
                        BottomNavItem(
                            name = "Home",
                            route = "home",
                            icon = painterResource(id = R.drawable.home),
                        ),
                        BottomNavItem(
                            name = "Search",
                            route = "on_boarding",
                            icon = painterResource(id = R.drawable.search),
                        ),
                        BottomNavItem(
                            name = "Chat",
                            route = "login",
                            icon = painterResource(id = R.drawable.chat),
                        ),
                        BottomNavItem(
                            name = "Profile",
                            route = "profile",
                            icon = painterResource(id = R.drawable.profile),
                        ),
                    ),
                    navController = navController,
                    onItemClick = {
                        navController.navigate(it.route)
                    })
            }
        }
    }
}

