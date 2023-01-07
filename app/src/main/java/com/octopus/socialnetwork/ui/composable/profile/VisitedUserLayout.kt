package com.octopus.socialnetwork.ui.composable.profile

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.composable.CircleButton
import com.octopus.socialnetwork.ui.composable.ReduceButton
import com.octopus.socialnetwork.ui.composable.SpaceHorizontally8dp
import com.octopus.socialnetwork.ui.screen.profile.state.ProfileUiState
import com.octopus.socialnetwork.ui.util.UserRelationUiState

@Composable
fun VisitedProfileLayout(
    state: ProfileUiState,
    onClickAddFriend: (Int) -> Unit,
    onClickRemoveFriend: (Int) -> Unit,
    onClickMessage: (Int) -> Unit,
) {
    if (state.userDetails.relation == UserRelationUiState.NOT_FRIEND || state.userDetails.relation == UserRelationUiState.REQUESTED) {
        ReduceButton(
            onClick = { onClickAddFriend(state.userDetails.userId) },
            isSelected = state.isRequestExists,
            idTitleResource = if (state.isRequestExists) R.string.requested else R.string.add_friend,
            idIconResource = R.drawable.add_person,
        )
    } else {
        ReduceButton(
            onClick = { onClickRemoveFriend(state.userDetails.userId) },
            isSelected = state.isRequestExists,
            idTitleResource = R.string.remove,
            idIconResource = R.drawable.add_person,
        )
    }

    SpaceHorizontally8dp()
    CircleButton(
        modifier = Modifier
            .height(25.dp)
            .width(29.dp),
        onClick = { onClickMessage(state.userDetails.userId) },
        idIconResource = R.drawable.massage,
        idTitleResource = R.string.send_message
    )
}