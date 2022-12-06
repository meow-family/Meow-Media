package com.octopus.socialnetwork.ui.screen.main

import android.annotation.SuppressLint
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.octopus.socialnetwork.ui.navigate.SocialNetworkNavGraph
import com.octopus.socialnetwork.ui.screen.home.HomeScreen
import com.octopus.socialnetwork.ui.theme.SocialNetworkTheme


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
@ExperimentalPagerApi
@ExperimentalMaterialApi
fun SocialNetworkApp() {
    SocialNetworkTheme {
        Scaffold {
            val navController = rememberNavController()
            SocialNetworkNavGraph(navController)
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