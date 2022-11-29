package com.octopus.socialnetwork.ui.screen.post

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.screen.post.composibale.BackgroundImage
import com.octopus.socialnetwork.ui.screen.post.composibale.PostDescription
import com.octopus.socialnetwork.ui.screen.post.composibale.ProfileImage
import com.octopus.socialnetwork.ui.screen.post.composibale.UserDetails
import com.octopus.socialnetwork.ui.theme.Transparent

@Composable
fun PostScreen() {
    BackgroundImage()
    PostDetails()
    PostContent()
}

@Composable
fun PostDetails() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.End
    ) {
        Card(
            modifier = Modifier
                .height(300.dp)
                .width(48.dp),
            shape = RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp),
            backgroundColor = Transparent,
        ) {
            Column(
                modifier = Modifier.padding(8.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_like),
                        contentDescription = "Like Icon",
                        tint = Color.White
                    )
                    Text(text = "15", color = Color.White)
                }
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Default.Comment,
                        contentDescription = "Comment Icon",
                        tint = Color.White
                    )
                    Text(text = "50", color = Color.White)
                }
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "Share Icon",
                        tint = Color.White
                    )
                    Text(text = "16", color = Color.White)
                }
            }
        }
    }
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