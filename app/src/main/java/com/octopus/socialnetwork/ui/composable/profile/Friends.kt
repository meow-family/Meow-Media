package com.octopus.socialnetwork.ui.composable.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.composable.CircleButton
import com.octopus.socialnetwork.ui.composable.Divider
import com.octopus.socialnetwork.ui.composable.ImageNetwork
import com.octopus.socialnetwork.ui.screen.profile.state.UserDetailsUiState
import com.octopus.socialnetwork.ui.theme.*
import com.octopus.socialnetwork.ui.util.UserRelationUiState

@Composable
fun Friends(
    state: List<UserDetailsUiState>,
    onClickItem: (Int) -> Unit,
    onClickRemoveFriend: (Int) -> Unit,
) {
    LazyColumn(
        Modifier.padding(top = spacingXSmall, bottom = spacingExtraLarge)
    ) {
        items(state) { user ->
            SingleFriend(
                state = user,
                userRelationUiState = UserRelationUiState.IS_FRIEND,
                onClickItem = onClickItem,
                onClickRemoveFriend = onClickRemoveFriend
            )
        }
    }
}

@Composable
fun SingleFriend(
    state: UserDetailsUiState,
    userRelationUiState: UserRelationUiState = UserRelationUiState.UNKNOWN,
    onClickItem: (Int) -> Unit,
    onClickMessage: (Int) -> Unit = {},
    onClickAccept: (Int) -> Unit = {},
    onClickDecline: (Int) -> Unit = {},
    onClickRemoveFriend: (Int) -> Unit = {},
    hiddenChat: Boolean = false
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

        ImageNetwork(
            imageUrl = state.profileAvatar,
            modifier = Modifier
                .clip(CircleShape)
                .size(48.dp),
            contentDescription = stringResource(R.string.profile_image),
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
        when (userRelationUiState) {
            UserRelationUiState.REQUESTED -> {

            }

            UserRelationUiState.IS_FRIEND -> {

            }

            else -> {
                if (!hiddenChat)
                    CircleButton(
                        modifier = Modifier
                            .height(30.dp)
                            .width(50.dp),
                        onClick = { onClickMessage(state.userId) },
                        iconModifier = Modifier.size(15.dp),
                        shape = Shapes.medium,
                        idIconResource = R.drawable.massage,
                        idTitleResource = R.string.send_message
                    )
            }
        }


    }

    Divider()


}