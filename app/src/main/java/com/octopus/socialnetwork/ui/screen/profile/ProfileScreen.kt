package com.octopus.socialnetwork.ui.screen.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.octopus.socialnetwork.ui.composable.Divider
import com.octopus.socialnetwork.ui.composable.ImageForEmptyList
import com.octopus.socialnetwork.ui.composable.SpaceVertically8dp
import com.octopus.socialnetwork.ui.composable.lotties.LottieError
import com.octopus.socialnetwork.ui.composable.lotties.LottieLoading
import com.octopus.socialnetwork.ui.composable.profile.MyProfileLayout
import com.octopus.socialnetwork.ui.composable.profile.ProfilePostItem
import com.octopus.socialnetwork.ui.composable.profile.UserDetails
import com.octopus.socialnetwork.ui.composable.profile.VisitedProfileLayout
import com.octopus.socialnetwork.ui.navigation.AuthenticationRoute
import com.octopus.socialnetwork.ui.screen.chat.navigateToChat
import com.octopus.socialnetwork.ui.screen.edit_profile.navigateToEditeProfileRoute
import com.octopus.socialnetwork.ui.screen.post.navigateToPostScreen
import com.octopus.socialnetwork.ui.screen.profile.uistate.ProfileUiState
import com.octopus.socialnetwork.ui.theme.spacingSmall


@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()

    ProfileContent(
        state = state,
        onClickAddFriend = viewModel::onClickAddFriend,
        onClickMessage = navController::navigateToChat,
        onClickEditProfile = navController::navigateToEditeProfileRoute,
        onClickLogout = viewModel::onClickLogout,
        onClickBack = { navController.popBackStack() },
        onClickPost = { postId, postOwnerId ->
            navController.navigateToPostScreen(postId, postOwnerId)
        },
        onClickTryAgain = viewModel::onClickTryAgain
    )
    if (state.isLogout) {
        navController.navigate(AuthenticationRoute.OnBoarding)
    }
}

@Composable
private fun ProfileContent(
    state: ProfileUiState,
    onClickBack: () -> Unit,
    onClickAddFriend: (Int) -> Unit,
    onClickMessage: (Int) -> Unit,
    onClickPost: (Int, Int) -> Unit,
    onClickLogout: () -> Unit,
    onClickEditProfile: (Int) -> Unit,
    onClickTryAgain: () -> Unit,
) {

    if (state.isLoading) {
        LottieLoading()
    } else {

        LazyVerticalGrid(
            modifier = Modifier
                .background(MaterialTheme.colors.background)
                .fillMaxSize(),
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(spacingSmall),
            horizontalArrangement = Arrangement.Center
        ) {


            item(span = { GridItemSpan(3) }) {

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    UserDetails(state.userDetails)

                    Row {
                        if (state.isMyProfile) {
                            MyProfileLayout(
                                state = state,
                                onClickEditProfile = onClickEditProfile,
                                onClickLogout = onClickLogout
                            )
                        } else {
                            VisitedProfileLayout(
                                state = state,
                                onClickAddFriend = onClickAddFriend,
                                onClickMessage = onClickMessage
                            )
                        }
                    }
                    SpaceVertically8dp()
                    Divider()
                }

            }

            if (state.isError) {
                item(span = { GridItemSpan(3) }) { LottieError(onClickTryAgain) }
            } else if (state.profilePosts.isEmpty()) {
                item(span = { GridItemSpan(3) }) { ImageForEmptyList() }
            } else {
                items(items = state.profilePosts) { ProfilePostUiState ->
                    ProfilePostItem(
                        post = ProfilePostUiState,
                        onClickPost = onClickPost
                    )
                }
            }
        }

    }
}



