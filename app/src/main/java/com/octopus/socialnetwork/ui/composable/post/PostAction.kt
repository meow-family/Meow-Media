package com.octopus.socialnetwork.ui.composable.post

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Comment
import androidx.compose.material.icons.sharp.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.composable.SpaceVertically24dp
import com.octopus.socialnetwork.ui.composable.SpaceVertically8dp

@Composable
fun PostAction(
    likeCount: String,
    commentCount: String,
    shareCount: String,
    like: () -> Unit,
    comment: () -> Unit,
    share: () -> Unit

) {
    Column(
        modifier = Modifier.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


        Icon(
            modifier = Modifier.clickable { like },
            painter = painterResource(R.drawable.ic_like),
            contentDescription = stringResource(R.string.like_icon),
            tint = Color.White
        )
        SpaceVertically8dp()
        Text(text = likeCount, color = Color.White)
        SpaceVertically24dp()
        Icon(
            modifier = Modifier.clickable { comment },
            imageVector = Icons.Sharp.Comment,
            contentDescription = stringResource(R.string.comment_icon),
            tint = Color.White
        )
        SpaceVertically8dp()
        Text(text = commentCount, color = Color.White)
        SpaceVertically24dp()
        Icon(
            modifier = Modifier.clickable { share },
            imageVector = Icons.Sharp.Share,
            contentDescription = stringResource(R.string.share_icon),
            tint = Color.White
        )
        Text(text = shareCount, color = Color.White)
    }
}