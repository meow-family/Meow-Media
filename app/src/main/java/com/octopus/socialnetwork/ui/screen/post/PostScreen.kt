package com.octopus.socialnetwork.ui.screen.post

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.octopus.socialnetwork.ui.screen.post.composibale.*
import com.octopus.socialnetwork.ui.theme.Transparent

@Composable
fun PostScreen() {
    BackgroundImage()
    PostContent()
}

@Composable
fun PostContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(145.dp),
            elevation = 0.dp,
            backgroundColor = Transparent,
            shape = RoundedCornerShape(0.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 16.dp,
                        vertical = 16.dp
                    )
            ) {
                Row {
                    ProfileImage()
                    Spacer(modifier = Modifier.width(8.dp))
                    UserDetails()
                }
                Spacer(modifier = Modifier.height(14.dp))
                PostDescription()
            }
        }
    }
}