package com.octopus.socialnetwork.ui.screen.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.octopus.socialnetwork.ui.composable.Divider
import com.octopus.socialnetwork.ui.composable.ImageForEmptyList
import com.octopus.socialnetwork.ui.composable.SpaceVertically8dp
import com.octopus.socialnetwork.ui.composable.lotties.LottieError
import com.octopus.socialnetwork.ui.composable.lotties.LottieLoading
import com.octopus.socialnetwork.ui.composable.profile.*
import com.octopus.socialnetwork.ui.screen.edit_profile.navigateToEditeProfileRoute
import com.octopus.socialnetwork.ui.screen.messaging.chat.navigateToChat
import com.octopus.socialnetwork.ui.screen.post.navigateToPostScreen
import com.octopus.socialnetwork.ui.screen.profile.state.ProfileUiState
import com.octopus.socialnetwork.ui.theme.spacingExtraLarge
import com.octopus.socialnetwork.ui.theme.spacingSmall
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProfileScreen(
    navController: NavController, viewModel: ProfileViewModel = hiltViewModel()
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
    }
    val posts = state.profilePosts.collectAsLazyPagingItems()
    val isEmptyFlow = posts.itemSnapshotList.isEmpty()

    ModalBottomSheetLayout(sheetState = sheetState,
        modifier = Modifier.padding(top = 20.dp),
        sheetShape = RoundedCornerShape(topEnd = 16.dp, topStart = 16.dp),
        sheetContent = {
            Friends(state.friends, onClickItem)
        }) {

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
                    UserDetails(state.userDetails,
                        onClickShowFriends = { scope.launch { sheetState.show() } })

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

            when {
                state.isLoading -> item(span = { GridItemSpan(3) }) {
                    LottieLoading(modifier = Modifier.padding(vertical = spacingExtraLarge))
                }
                state.isError -> item(span = { GridItemSpan(3) }) {
                    LottieError(onClickTryAgain)
                }
                isEmptyFlow -> item(span = { GridItemSpan(3) }) {
                    ImageForEmptyList()
                }
                else -> {
                    items(posts.itemCount) { index ->
                        posts[index]?.let {
                            ProfilePostItem(post = it, onClickPost = onClickPost)
                        }
                    }
                }
            }
            when (posts.loadState.append) {
                is LoadState.NotLoading -> Unit
                LoadState.Loading -> {
                    item {  LottieLoading(modifier = Modifier.size(70.dp)) }
                }
                is LoadState.Error -> {
                    item { LottieLoading(modifier = Modifier.size(70.dp)) }
                }
            }

        }
    }

}


