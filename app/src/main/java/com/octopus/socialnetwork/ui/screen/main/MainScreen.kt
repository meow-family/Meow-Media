package com.octopus.socialnetwork.ui.screen.main

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.pager.ExperimentalPagerApi
import com.octopus.socialnetwork.ui.screen.login.LoginScreen
import com.octopus.socialnetwork.ui.screen.profile.ProfileScreen
import com.octopus.socialnetwork.ui.screen.register.RegisterScreen
import com.octopus.socialnetwork.ui.theme.SocialNetworkTheme

@ExperimentalPagerApi
@Preview(showSystemUi = true)
@Composable
fun SocialNetworkApp() {
    SocialNetworkTheme {
        Scaffold {
        //  HomeScreen()
            ProfileScreen()
//            LoginScreen()
            //ProfileScreen()
        }
    }

}