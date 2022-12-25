package com.octopus.socialnetwork.ui.composable.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.composable.SpaceHorizontally16dp
import com.octopus.socialnetwork.ui.composable.SpaceHorizontally4dp
import com.octopus.socialnetwork.ui.composable.SpaceVertically10dp
import com.octopus.socialnetwork.ui.composable.SpaceVertically8dp
import com.octopus.socialnetwork.ui.screen.profile.uistate.UserDetailsUiState
import com.octopus.socialnetwork.ui.theme.textPrimaryColor

@Composable
fun UserDetails(
    state: UserDetailsUiState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    )
    {
        ProfileInformation(
            backgroundProfileImageUrl = state.profileCover,
            profileImageUrl = state.profileAvatar,
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = state.fullName,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                color = MaterialTheme.colors.textPrimaryColor,
                style = MaterialTheme.typography.h5,
            )

            LightText(
                text = state.username,
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )

            SpaceVertically10dp()

            Row(modifier = Modifier.align(Alignment.CenterHorizontally))
            {

                Text(
                    text = state.friendsCount,
                    style = MaterialTheme.typography.subtitle2.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = MaterialTheme.colors.textPrimaryColor,
                )
                SpaceHorizontally4dp()
                LightText(stringResource(R.string.friends))
                SpaceHorizontally16dp()
                Text(
                    text = state.postCount,
                    style = MaterialTheme.typography.subtitle2.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = MaterialTheme.colors.textPrimaryColor,
                )

                SpaceHorizontally4dp()
                LightText(stringResource(R.string.posts))

            }
            SpaceVertically8dp()

        }

    }
}