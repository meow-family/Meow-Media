package com.octopus.socialnetwork.ui.composable.profile

import androidx.compose.runtime.Composable
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.composable.CircleButton
import com.octopus.socialnetwork.ui.composable.ReduceButton
import com.octopus.socialnetwork.ui.composable.SpaceHorizontally8dp
import com.octopus.socialnetwork.ui.screen.profile.state.ProfileUiState

@Composable
fun MyProfileLayout(
    state: ProfileUiState,
    onClickEditProfile: (Int) -> Unit,
    onClickLogout: () -> Unit,
) {
    ReduceButton(
        onClick = {onClickEditProfile(state.userDetails.userId)},
        idTitleResource = R.string.edit_profile,
        idIconResource = R.drawable.edite_profile,
    )
    SpaceHorizontally8dp()
    CircleButton(
        onClick = onClickLogout,
        idIconResource = R.drawable.logout,
        idTitleResource = R.string.logout
    )
}