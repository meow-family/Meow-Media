package com.octopus.socialnetwork.ui.composable.profile

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.octopus.socialnetwork.ui.screen.profile.uistate.UserDetailsUiState
import com.octopus.socialnetwork.ui.theme.Shapes
import com.octopus.socialnetwork.ui.theme.spacing
import com.octopus.socialnetwork.ui.theme.zero

@Composable
fun ReduceButtonEditProfile(
    onClick: (Int) -> Unit,
    @StringRes idTitleResource: Int,
    @DrawableRes idIconResource: Int,
    isSelected: Boolean = false,
    state: UserDetailsUiState
) {
    Button(
        onClick = { onClick(state.userId) },
        modifier = Modifier
            .height(25.dp)
            .width(87.dp),
        shape = Shapes.large,
        contentPadding = PaddingValues(zero),
        colors = ButtonDefaults.buttonColors(
            if (isSelected) MaterialTheme.colors.secondary else MaterialTheme.colors.primary
        )
    ) {
        Image(
            painterResource(idIconResource),
            contentDescription = stringResource(idTitleResource),
            modifier = Modifier.size(14.dp)
        )

        Text(
            stringResource(idTitleResource),
            Modifier.padding(start = spacing, top = spacing),
            style = MaterialTheme.typography.overline,
            color = if (isSelected) MaterialTheme.colors.onSecondary else MaterialTheme.colors.onPrimary,
        )
    }
}