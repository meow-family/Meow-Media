package com.octopus.socialnetwork.ui.screen.friend_request

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.composable.AppBar
import com.octopus.socialnetwork.ui.composable.ImageForEmptyList
import com.octopus.socialnetwork.ui.composable.friend_requests.UserDetailsItem
import com.octopus.socialnetwork.ui.composable.lotties.LottieError
import com.octopus.socialnetwork.ui.composable.lotties.LottieLoading
import com.octopus.socialnetwork.ui.screen.friend_request.state.FriendRequestUiState
import com.octopus.socialnetwork.ui.screen.profile.navigateToUserProfileScreen
import com.octopus.socialnetwork.ui.screen.profile.uistate.UserDetailsUiState
import com.octopus.socialnetwork.ui.theme.SocialNetworkTheme
import com.octopus.socialnetwork.ui.theme.outLine
import com.octopus.socialnetwork.ui.util.UserRelationUiState

@Composable
fun FriendRequestScreen(
    navController: NavController,
    viewModel: FriendRequestViewModel = hiltViewModel(),
) {

    val state by viewModel.state.collectAsState()
    FriendRequestContent(
        state = state,
        onClickAccept = viewModel::onClickAccept,
        onClickDecline = viewModel::onClickDecline,
        onClickRequest = navController::navigateToUserProfileScreen,
        onClickTryAgain = viewModel::onClickTryAgain
    ) { navController.popBackStack() }
}


@Composable
private fun FriendRequestContent(
    state: FriendRequestUiState,
    onClickAccept: (Int) -> Unit,
    onClickDecline: (Int) -> Unit,
    onClickRequest: (Int) -> Unit,
    onClickTryAgain: () -> Unit,
    onClickBack: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
    ) {

        AppBar(
            onClickBack = onClickBack,
            title = stringResource(id = R.string.friend_requests)
        )

        Divider(color = MaterialTheme.colors.outLine, thickness = 1.dp)

        if (state.isLoading) {
            LottieLoading()
        } else if (state.isError) {
            LottieError(onClickTryAgain)
        } else if (state.requests.isEmpty()) {
            ImageForEmptyList(modifier = Modifier.align(alignment = Alignment.CenterHorizontally))
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                if (state.requests.isEmpty()) {
                    item { ImageForEmptyList(modifier = Modifier.padding(vertical = 100.dp)) }
                } else {
                    items(state.requests) {
                        UserDetailsItem(
                            state = it,
                            friendRequest = true,
                            onClickAccept = onClickAccept,
                            onClickDecline = onClickDecline,
                            onClickItem = onClickRequest
                        )
                    }
                }
            }
        }

    }
}

@Preview(showSystemUi = true)
@Composable
fun FriendRequestScreenPreview() {
    SocialNetworkTheme {
        FriendRequestContent(
            state = FriendRequestUiState(
                requests = listOf(
                    UserDetailsUiState(
                        userId = 0,
                        fullName = "Ameer Amjed",
                        username = "AmeerAmjed",
                        friendsCount = "0",
                        profileAvatar = "",
                        profileCover = "",
                        relation = UserRelationUiState.ME
                    ),

                    ),
                isLoading = false,
                isError = false,
            ),
            onClickAccept = {},
            onClickDecline = {},
            onClickRequest = {},
            onClickTryAgain = {},
            onClickBack = {}
        )
    }
}


