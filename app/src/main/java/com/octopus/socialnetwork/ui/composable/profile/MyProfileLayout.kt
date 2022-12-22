package com.octopus.socialnetwork.ui.composable.profile

import androidx.compose.runtime.Composable
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.composable.CircleButton
import com.octopus.socialnetwork.ui.composable.ReduceButton
import com.octopus.socialnetwork.ui.composable.SpaceHorizontally8dp

@Composable
fun MyProfileLayout(
    onClickEditProfile: () -> Unit,
    onClickLogout: () -> Unit,
) {
    ReduceButton(
        onClick = onClickEditProfile,
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