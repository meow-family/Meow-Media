package com.octopus.socialnetwork.ui.screen.profile
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.composable.CircleButton
import com.octopus.socialnetwork.ui.composable.Loading
import com.octopus.socialnetwork.ui.composable.ReduceButton
import com.octopus.socialnetwork.ui.composable.SpaceHorizontally8dp
import com.octopus.socialnetwork.ui.composable.SpaceVertically24dp
import com.octopus.socialnetwork.ui.composable.profile.ProfilePostItem
import com.octopus.socialnetwork.ui.composable.profile.TabContentProfile
import com.octopus.socialnetwork.ui.composable.profile.UserDetails
import com.octopus.socialnetwork.ui.screen.post.navigateToPostScreen
import com.octopus.socialnetwork.ui.screen.profile.uistate.ProfileUiState
import com.octopus.socialnetwork.ui.theme.spacingMedium
import com.octopus.socialnetwork.ui.theme.spacingSmall
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalPagerApi::class)
@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val profileContentPagerState = rememberPagerState(0)
    val coroutineScope = rememberCoroutineScope()
    val scrollScreenState = rememberScrollState()
    ProfileContent(
        state = state,
        scrollScreenState = scrollScreenState,
        profileContentPagerState = profileContentPagerState,
        coroutineScope = coroutineScope,
        onClickAddFriend = viewModel::onClickAddFriend,
        onClickMessage = viewModel::onClickMessage,
        onClickLogout = viewModel::onClickLogout,
        onClickEditeProfile = viewModel::onClickEditeProfile,
        onClickBack = { navController.popBackStack() },
        onClickPost = { postId, postOwnerId ->
            navController.navigateToPostScreen(postId, postOwnerId)
        },
    )
}

@Composable
@OptIn(ExperimentalPagerApi::class)
private fun ProfileContent(
    state: ProfileUiState,
    scrollScreenState: ScrollState,
    profileContentPagerState: PagerState,
    coroutineScope: CoroutineScope,
    onClickBack: () -> Unit,
    onClickAddFriend: () -> Unit,
    onClickMessage: () -> Unit,
    onClickPost: (Int, Int) -> Unit,
    onClickLogout: () -> Unit,
    onClickEditeProfile: () -> Unit,
) {

    if (state.isLoading) {
        Loading()
    } else {
        BoxWithConstraints {
            val screenHeight = maxHeight
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollScreenState),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    UserDetails(state.userDetails)

                    Row {
                        if (state.isUserVisitor) ReduceButton(
                            onClick = onClickAddFriend,
                            isSelected = state.isRequestSent,
                            idTitleResource = if (state.isRequestSent) R.string.requested else R.string.add_friend,
                            idIconResource = R.drawable.add_person,
                        ) else
                            ReduceButton(
                                onClick = onClickEditeProfile,
                                idTitleResource = R.string.edit_profile,
                                idIconResource = R.drawable.edite_profile,
                            )
                        SpaceHorizontally8dp()
                        if (state.isUserVisitor) CircleButton(
                            onClick = onClickMessage,
                            idIconResource = R.drawable.massage,
                            idTitleResource = R.string.send_message
                        ) else CircleButton(
                            onClick = onClickLogout,
                            idIconResource = R.drawable.logout,
                            idTitleResource = R.string.logout
                        )
                    }

                    SpaceVertically24dp()
                }

                Column(modifier = Modifier.height(screenHeight)) {
                    TabContentProfile(
                        state = state.profileContentTab,
                        activeTabState = profileContentPagerState.currentPage,
                    ) {
                        coroutineScope.launch {
                            profileContentPagerState.animateScrollToPage(it)
                        }
                    }
                    HorizontalPager(
                        modifier = Modifier,
                        count = 2,
                        state = profileContentPagerState,
                    ) { page ->
                        when (page) {
                            0 -> {
                                LazyVerticalGrid(
                                    columns = GridCells.Fixed(3),
                                    contentPadding = PaddingValues(spacingMedium),
                                    verticalArrangement = Arrangement.spacedBy(spacingSmall),
                                    horizontalArrangement = Arrangement.spacedBy(spacingSmall)
                                ) {

                                    items(items = state.profilePosts) { ProfilePostUiState ->
                                        ProfilePostItem(
                                            post = ProfilePostUiState,
                                            onClickPost = onClickPost
                                        )
                                    }

                                }
                            }

                            1 -> {
                                LazyVerticalGrid(
                                    columns = GridCells.Fixed(3),
                                    contentPadding = PaddingValues(spacingMedium),
                                    verticalArrangement = Arrangement.spacedBy(spacingSmall),
                                    horizontalArrangement = Arrangement.spacedBy(spacingSmall)
                                ) {

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
            }
        }

    }

}