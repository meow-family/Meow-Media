package com.octopus.socialnetwork.ui.screen.profile
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.octopus.socialnetwork.ui.composable.ButtonFollow
import com.octopus.socialnetwork.ui.composable.SpaceHorizontally8dp
import com.octopus.socialnetwork.ui.composable.SpaceVertically24dp
import com.octopus.socialnetwork.ui.composable.profile.ButtonMessage
import com.octopus.socialnetwork.ui.composable.profile.ProfilePostItem
import com.octopus.socialnetwork.ui.composable.profile.TabContentProfile
import com.octopus.socialnetwork.ui.composable.profile.UserDetails
import com.octopus.socialnetwork.ui.screen.post.navigateToPostScreen
import com.octopus.socialnetwork.ui.screen.profile.uistate.ProfileUiState
import com.octopus.socialnetwork.ui.theme.spacingMedium
import com.octopus.socialnetwork.ui.theme.spacingSmall


@OptIn(ExperimentalPagerApi::class)
@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()
    val contentTabPagerState = rememberPagerState(state.profileContentTab.selectedTabIndex)
    ProfileContent(
        state = state,
        contentTabPagerState = contentTabPagerState,
        onClickBack = { navController.popBackStack() },
        onClickFollow = viewModel::onClickFollow,
        onClickMessage = viewModel::onClickMessage,
        onClickPost = { postId, postOwnerId ->
            navController.navigateToPostScreen(postId, postOwnerId)
        },
        onChangeTabIndex = viewModel::onChangeTabIndex

    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun ProfileContent(
    state: ProfileUiState,
    contentTabPagerState: PagerState,
    onClickBack: () -> Unit,
    onClickFollow: () -> Unit,
    onClickMessage: () -> Unit,
    onChangeTabIndex: (index: Int) -> Unit,
    onClickPost: (Int, Int) -> Unit,
) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(spacingMedium),
        verticalArrangement = Arrangement.spacedBy(spacingSmall),
        horizontalArrangement = Arrangement.spacedBy(spacingSmall)
    ) {
        item(span = { GridItemSpan(3) }) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.background)
            ) {
                UserDetails(
                    state = state.userDetails
                )

                Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                    ButtonFollow(onFollow = onClickFollow)
                    SpaceHorizontally8dp()
                    ButtonMessage(onMessage = onClickMessage)
                }

                SpaceVertically24dp()

            }


        }

        item(span = { GridItemSpan(3) }) {
            TabContentProfile(
                state = state.profileContentTab,
                onChangeTabIndex = onChangeTabIndex
            )
        }

        if (state.profileContentTab.selectedTabIndex == 0) {
            items(items = state.profilePosts) { ProfilePostUiState ->
                ProfilePostItem(
                    post = ProfilePostUiState,
                    onClickPost = onClickPost
                )
            }
        } else {
            item {
                Text(text = "Albums")
            }
        }

    }



}