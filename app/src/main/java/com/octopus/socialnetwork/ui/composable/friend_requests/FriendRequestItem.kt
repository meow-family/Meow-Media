package com.octopus.socialnetwork.ui.composable.friend_requests

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.composable.Divider
import com.octopus.socialnetwork.ui.composable.SpaceHorizontally8dp
import com.octopus.socialnetwork.ui.theme.Black

@Preview
@Composable
fun FriendRequestItem(

) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable { },
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = R.drawable.iron_man),
            contentDescription = stringResource(R.string.profile_image),
            modifier = Modifier
                .clip(CircleShape)
                .size(48.dp),
            contentScale = ContentScale.Crop,
        )
        Text(
            modifier = Modifier.padding(start = 20.dp),
            text = "Iron Man",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Black,
        )
        Row(
            modifier = Modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.End
        ) {

            FriendRequestButton(
                text = "Decline",
                onClick = {},
                borderStroke = BorderStroke(0.5.dp, color = MaterialTheme.colors.primary),
                backgroundColor = MaterialTheme.colors.background,
                textColor = MaterialTheme.colors.primary
            )
            SpaceHorizontally8dp()
            FriendRequestButton(
                text = "Accept",
                onClick = {},
                borderStroke = BorderStroke(0.dp, color = MaterialTheme.colors.primary),
                backgroundColor = MaterialTheme.colors.primary,
                textColor = MaterialTheme.colors.onPrimary
            )
        }

    }

    Divider()
}