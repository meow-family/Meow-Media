package com.octopus.socialnetwork.ui.screen.main

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.pager.ExperimentalPagerApi
import com.octopus.socialnetwork.ui.screen.home.HomeScreen
import com.octopus.socialnetwork.ui.screen.notifications.NotificationsScreen
import com.octopus.socialnetwork.ui.theme.SocialNetworkTheme


@Composable
@ExperimentalPagerApi
@ExperimentalMaterialApi
fun SocialNetworkApp() {
    SocialNetworkTheme {
        NotificationsScreen()
//        Scaffold {
//            //  HomeScreen()
//            //RegisterScreen()
//
//            // RegisterScreen()
////            LoginScreen()
//        }
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