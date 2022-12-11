package com.octopus.socialnetwork.ui.screen.profile
//import com.octopus.socialnetwork.ui.composable.profile.TabProfile
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.composable.ButtonFollow
import com.octopus.socialnetwork.ui.composable.SpaceHorizontally16dp
import com.octopus.socialnetwork.ui.composable.SpaceHorizontally4dp
import com.octopus.socialnetwork.ui.composable.SpaceHorizontally8dp
import com.octopus.socialnetwork.ui.composable.SpaceVertically10dp
import com.octopus.socialnetwork.ui.composable.SpaceVertically8dp
import com.octopus.socialnetwork.ui.composable.profile.ButtonMessage
import com.octopus.socialnetwork.ui.composable.profile.ProfileInformation
import com.octopus.socialnetwork.ui.composable.profile.ProfilePostItem
import com.octopus.socialnetwork.ui.composable.profile.TabContentProfile
import com.octopus.socialnetwork.ui.screen.post.navigateToPostScreen
import com.octopus.socialnetwork.ui.screen.profile.uistate.ProfileUiState
import com.octopus.socialnetwork.ui.theme.PoppinsTypography
import com.octopus.socialnetwork.ui.theme.spacingMedium
import com.octopus.socialnetwork.ui.theme.textSecondaryColor


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

    Column(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .padding(spacingMedium)
            .fillMaxSize(),
//            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
    ) {

            ProfileInformation(
                backImageProfile = rememberAsyncImagePainter(model = state.profileCover),
                profileImage = rememberAsyncImagePainter(model = state.profileAvatar),

                )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(136.dp)
            ) {
                Text(
                    text = state.fullName,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontWeight = FontWeight.Bold,
                    fontFamily = PoppinsTypography.subtitle1.fontFamily,
                    fontStyle = PoppinsTypography.subtitle1.fontStyle,
                    fontSize = PoppinsTypography.subtitle1.fontSize,
                    color = MaterialTheme.colors.textSecondaryColor,
                    style = MaterialTheme.typography.h6,
                )
                Text(
                    text = state.username,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontWeight = FontWeight.Light,
                    color = MaterialTheme.colors.onSecondary,
                    fontFamily = PoppinsTypography.caption.fontFamily,
                    fontStyle = PoppinsTypography.caption.fontStyle,
                    fontSize = PoppinsTypography.caption.fontSize

                )

                SpaceVertically10dp()
                Row(modifier = Modifier.align(Alignment.CenterHorizontally))
                {

                    Text(
                        text = state.friendsCount,
                        fontWeight = FontWeight.Bold,
                        fontFamily = PoppinsTypography.caption.fontFamily,
                        fontStyle = PoppinsTypography.caption.fontStyle,
                        fontSize = PoppinsTypography.caption.fontSize
                    )
                    SpaceHorizontally4dp()
                    Text(
                        text = stringResource(R.string.friends),
                        fontWeight = FontWeight.W400,
                        fontFamily = PoppinsTypography.caption.fontFamily,
                        fontStyle = PoppinsTypography.caption.fontStyle,
                        fontSize = PoppinsTypography.caption.fontSize
                    )
                    SpaceHorizontally16dp()
                    Text(
                        text = state.postCount,
                        fontWeight = FontWeight.Bold,
                        fontFamily = PoppinsTypography.caption.fontFamily,
                        fontStyle = PoppinsTypography.caption.fontStyle,
                        fontSize = PoppinsTypography.caption.fontSize
                    )
                    SpaceHorizontally4dp()
                    Text(
                        text = stringResource(R.string.posts),
                        fontWeight = FontWeight.W400,
                        fontFamily = PoppinsTypography.caption.fontFamily,
                        fontStyle = PoppinsTypography.caption.fontStyle,
                        fontSize = PoppinsTypography.caption.fontSize
                    )


                }
                SpaceVertically8dp()
                Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                    ButtonFollow(onFollow = onClickFollow)
                    SpaceHorizontally8dp()
                    ButtonMessage(onMessage = onClickMessage)
                }

            }


            TabContentProfile(
                state = state.profileContentTab,
                onChangeTabIndex = onChangeTabIndex
            )
            HorizontalPager(
                count = state.profileContentTab.itemTabs.size,
                state = contentTabPagerState,
            ) { page ->
                when (page) {
                    0 -> {
                        LazyVerticalGrid(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = MaterialTheme.colors.background),
                            columns = GridCells.Fixed(3),
                            contentPadding = PaddingValues(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
//        item(span = { GridItemSpan(3) }) {
//
//        }

                            items(items = state.profilePosts) { ProfilePostUiState ->
                                ProfilePostItem(
                                    post = ProfilePostUiState,
                                    onClickPost = onClickPost
                                )
                            }
                        }

                    }

                    1 -> {

                    }
                }

            }

//                Divider()


    }


}