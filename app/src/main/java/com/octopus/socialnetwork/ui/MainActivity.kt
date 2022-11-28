package com.octopus.socialnetwork.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Modifier
import com.octopus.socialnetwork.ui.theme.SocialNetworkTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SocialNetworkTheme {
                Card(modifier = Modifier.background(color = MaterialTheme.colors.primary)) {
                }
            }
        }
    }
}


