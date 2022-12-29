package com.octopus.socialnetwork.ui.screen.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.octopus.socialnetwork.ui.composable.Divider
import com.octopus.socialnetwork.ui.composable.ImageForEmptyList
import com.octopus.socialnetwork.ui.composable.SpaceVertically8dp
import com.octopus.socialnetwork.ui.composable.lotties.LottieError
import com.octopus.socialnetwork.ui.composable.lotties.LottieLoading
import com.octopus.socialnetwork.ui.composable.profile.Friends
import com.octopus.socialnetwork.ui.composable.profile.MyProfileLayout
import com.octopus.socialnetwork.ui.composable.profile.ProfilePostItem
import com.octopus.socialnetwork.ui.composable.profile.UserDetails
import com.octopus.socialnetwork.ui.composable.profile.VisitedProfileLayout
import com.octopus.socialnetwork.ui.screen.edit_profile.navigateToEditeProfileRoute
import com.octopus.socialnetwork.ui.screen.messaging.chat.navigateToChat
import com.octopus.socialnetwork.ui.screen.onboarding.navigateToOnBoarding
import com.octopus.socialnetwork.ui.screen.post.navigateToPostScreen
import com.octopus.socialnetwork.ui.screen.profile.uistate.ProfileUiState
import com.octopus.socialnetwork.ui.theme.spacingSmall
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel()
) {

    val sheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()
    val state by viewModel.state.collectAsState()

    ProfileContent(
        state = state,
        sheetState = sheetState,
        scope = scope,
        onClickAddFriend = viewModel::onClickAddFriend,
        onClickMessage = navController::navigateToChat,
        onClickEditProfile = navController::navigateToEditeProfileRoute,
        onClickTryAgain = viewModel::onClickTryAgain,
        onClickLogout = viewModel::onClickLogout,
        onClickBack = { navController.popBackStack() },
        onClickPost = { postId, postOwnerId ->
            navController.navigateToPostScreen(postId, postOwnerId)
        },
        onClickItem = {
            navController.navigateToUserProfileScreen(it)
            scope.launch { sheetState.hide() }
        },

        )
    if (state.isLogout) {
        navController.navigateToOnBoarding()
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ProfileContent(
    state: ProfileUiState,
    sheetState: ModalBottomSheetState,
    scope: CoroutineScope,
    onClickBack: () -> Unit,
    onClickAddFriend: (Int) -> Unit,
    onClickMessage: (Int) -> Unit,
    onClickPost: (Int, Int) -> Unit,
    onClickLogout: () -> Unit,
    onClickEditProfile: (Int) -> Unit,
    onClickTryAgain: () -> Unit,
    onClickItem: (Int) -> Unit,
) {

    if (state.isLoading) {
        LottieLoading()
    } else {

        ModalBottomSheetLayout(
            sheetState = sheetState,
            modifier = Modifier.padding(top = 20.dp),
            sheetShape = RoundedCornerShape(topEnd = 16.dp, topStart = 16.dp),
            sheetContent = {
                Friends(state.friends, onClickItem)
            })
        {

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
                        UserDetails(
                            state.userDetails,
                            onClickShowFriends = { scope.launch { sheetState.show() } }
                        )

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
}







