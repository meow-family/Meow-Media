package com.octopus.socialnetwork.ui.composable.friend_requests

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.composable.SpaceHorizontally8dp


@Composable
fun FriendRequest(
    modifier: Modifier = Modifier,
    onClickAccept: () -> Unit,
    onClickDecline: () -> Unit,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        FriendRequestButton(
            text = stringResource(id = R.string.decline),
            onClick = onClickDecline,
            borderStroke = BorderStroke(0.5.dp, color = MaterialTheme.colors.primary),
            backgroundColor = MaterialTheme.colors.background,
            textColor = MaterialTheme.colors.primary
        )
        SpaceHorizontally8dp()
        FriendRequestButton(
            text = stringResource(id = R.string.Accept),
            onClick = onClickAccept,
            borderStroke = BorderStroke(0.dp, color = MaterialTheme.colors.primary),
            backgroundColor = MaterialTheme.colors.primary,
            textColor = MaterialTheme.colors.onPrimary
        )
    }
}
