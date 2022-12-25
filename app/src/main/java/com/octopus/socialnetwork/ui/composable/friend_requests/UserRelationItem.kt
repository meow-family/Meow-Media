package com.octopus.socialnetwork.ui.composable.friend_requests

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.composable.Divider
import com.octopus.socialnetwork.ui.composable.SpaceHorizontally8dp
import com.octopus.socialnetwork.ui.composable.customImageLoad
import com.octopus.socialnetwork.ui.screen.profile.uistate.UserDetailsUiState
import com.octopus.socialnetwork.ui.theme.SocialNetworkTheme
import com.octopus.socialnetwork.ui.theme.spacingMedium
import com.octopus.socialnetwork.ui.theme.textPrimaryColor
import com.octopus.socialnetwork.ui.util.enums.UserRelationUiState
import com.octopus.socialnetwork.ui.util.fake_data.FakeData


@Composable
fun UserRelationItem(
    state: UserDetailsUiState,
    onClickItem: (Int) -> Unit,
    friendRequest: Boolean = false,
    onClickAccept: (Int) -> Unit = {},
    onClickDecline: (Int) -> Unit = {},
    onClickAddFriend: (Int) -> Unit = {},
    onClickRemoveFriend: (Int) -> Unit = {},
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .background(MaterialTheme.colors.background)
            .clickable { onClickItem(state.userId) }
            .padding(10.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = customImageLoad(state.profileAvatar),
            contentDescription = stringResource(R.string.profile_image),
            modifier = Modifier
                .clip(CircleShape)
                .size(48.dp),
            contentScale = ContentScale.Crop,
        )
        Text(
            modifier = Modifier
                .padding(horizontal = spacingMedium)
                .weight(1f),
            text = state.fullName,
            style = MaterialTheme.typography.h6.copy(
                color = MaterialTheme.colors.textPrimaryColor,
                fontWeight = FontWeight.Medium,
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        if (state.relation == UserRelationUiState.REQUESTED || friendRequest) {
            Row(
                modifier = Modifier
                    .weight(1f, fill = false),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
            ) {

                FriendRequestButton(
                    text = stringResource(id = R.string.decline),
                    onClick = {
                        onClickDecline(state.userId)
                    },
                    borderStroke = BorderStroke(0.5.dp, color = MaterialTheme.colors.primary),
                    backgroundColor = MaterialTheme.colors.background,
                    textColor = MaterialTheme.colors.primary
                )
                SpaceHorizontally8dp()
                FriendRequestButton(
                    text = stringResource(id = R.string.Accept),
                    onClick = { onClickAccept(state.userId) },
                    borderStroke = BorderStroke(0.dp, color = MaterialTheme.colors.primary),
                    backgroundColor = MaterialTheme.colors.primary,
                    textColor = MaterialTheme.colors.onPrimary
                )
            }
        } else if (state.relation == UserRelationUiState.NOT_FRIEND) {
            FriendRequestButton(
                text = stringResource(id = R.string.add),
                onClick = { onClickAddFriend(state.userId) },
                borderStroke = BorderStroke(0.dp, color = MaterialTheme.colors.primary),
                backgroundColor = MaterialTheme.colors.primary,
                textColor = MaterialTheme.colors.onPrimary
            )
        } else if (state.relation == UserRelationUiState.IS_FRIEND) {
            FriendRequestButton(
                text = stringResource(id = R.string.remove_friend),
                onClick = { onClickRemoveFriend(state.userId) },
                borderStroke = BorderStroke(0.dp, color = MaterialTheme.colors.primary),
                backgroundColor = MaterialTheme.colors.primary,
                textColor = MaterialTheme.colors.onPrimary
            )
        }


    }

    Divider()
}

@Preview("me")
@Composable
fun UserRelationItemPreviewMe() {
    SocialNetworkTheme {
        UserRelationItem(
            state = FakeData.meUserDetailsUiState,
            onClickAccept = {},
            onClickDecline = {},
            onClickItem = {},
        )
    }
}

@Preview("friend")
@Composable
fun UserRelationItemPreviewFriend() {
    SocialNetworkTheme {
        UserRelationItem(
            state = FakeData.friendUserDetailsUiState,
            onClickAccept = {},
            onClickDecline = {},
            onClickItem = {},
        )
    }
}


@Preview("notFriend")
@Composable
fun UserRelationItemPreviewNotFriend() {
    SocialNetworkTheme {
        UserRelationItem(
            state = FakeData.notFriendUserDetailsUiState,
            onClickAccept = {},
            onClickDecline = {},
            onClickItem = {},
        )
    }
}

@Preview("friend")
@Composable
fun UserRelationItemPreview() {
    SocialNetworkTheme {
        UserRelationItem(
            state = FakeData.RequestedUserDetailsUiState,
            onClickAccept = {},
            onClickDecline = {},
            onClickItem = {},
        )
    }
}