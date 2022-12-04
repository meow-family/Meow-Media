package com.octopus.socialnetwork.ui.screen.main

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.pager.ExperimentalPagerApi
import com.octopus.socialnetwork.ui.screen.home.HomeScreen
import com.octopus.socialnetwork.ui.screen.profile.ProfileScreen
import com.octopus.socialnetwork.ui.theme.SocialNetworkTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalPagerApi
@Preview(showSystemUi = true)
@Composable
fun SocialNetworkApp() {
    SocialNetworkTheme {
        Scaffold {
           HomeScreen()
            //ProfileScreen()
            // RegisterScreen()
            //LoginScreen()
           // PostScreen()
        }
    }

}