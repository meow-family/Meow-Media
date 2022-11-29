package com.octopus.socialnetwork.ui.screen.post

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.screen.post.composibale.BackgroundImage
import com.octopus.socialnetwork.ui.theme.Transparent

@Composable
fun PostScreen() {
    BackgroundImage()
    PostContent()
}

@Composable
fun PostContent() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
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
                    Image(
                        painter = painterResource(R.drawable.login_background),
                        contentDescription = stringResource(R.string.description),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            text = "Moataz",
                            color = Color.White,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "@moataz_h",
                            color = Color.White,
                            fontSize = 12.sp
                        )
                    }
                }
                Spacer(modifier = Modifier.height(14.dp))
                Text(
                    modifier = Modifier.padding(end = 22.dp),
                    text = "This is my rabbit it is so cute her name kiu kgkg gkmggrkmkgmkmgkmkgrkmgkrgrgrggr lglorg but before 4 years she is dead (I am sad for that, and I still miss her)",
                    color = Color.White, fontSize = 14.sp,
                    maxLines = 3,
                )
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun PostScreenPreview() {
    PostScreen()
}