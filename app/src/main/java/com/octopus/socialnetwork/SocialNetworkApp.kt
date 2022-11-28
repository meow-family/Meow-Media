package com.octopus.socialnetwork

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.octopus.socialnetwork.ui.screen.login.LoginScreen
import com.octopus.socialnetwork.ui.screen.signUp.SignUpScreen
import com.octopus.socialnetwork.ui.theme.SocialNetworkTheme


@Preview(showSystemUi = true)
@Composable
fun SocialNetworkApp() {
    SocialNetworkTheme {
        Scaffold
        {
            LoginScreen()
            SignUpScreen()
        }
    }

}