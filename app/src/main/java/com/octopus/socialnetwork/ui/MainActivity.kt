package com.octopus.socialnetwork.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.accompanist.pager.ExperimentalPagerApi
import com.octopus.socialnetwork.SocialNetworkApp

@ExperimentalPagerApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SocialNetworkApp()
        }
    }
}

