package com.octopus.socialnetwork.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.octopus.socialnetwork.ui.composable.ImageForEmptyList
import com.octopus.socialnetwork.ui.composable.ItemPost
import com.octopus.socialnetwork.ui.composable.home.TopBar
import com.octopus.socialnetwork.ui.composable.lotties.LottieError
import com.octopus.socialnetwork.ui.composable.lotties.LottieLoading
import com.octopus.socialnetwork.ui.screen.comments.navigateToCommentsScreen
import com.octopus.socialnetwork.ui.screen.friend_request.navigateToFriendRequests
import com.octopus.socialnetwork.ui.screen.home.state.HomeUiState
import com.octopus.socialnetwork.ui.screen.notifications.navigateToNotificationsScreen
import com.octopus.socialnetwork.ui.screen.post.navigateToPostScreen


@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.homeUiState.collectAsState()


    HomeContent(
        state = state,
        onClickLike = viewModel::onClickLike,
        onClickComment = { postId ->
            navController.navigateToCommentsScreen(postId, "post")
        },
        onClickPost = { postId, postOwnerId ->
            navController.navigateToPostScreen(postId, postOwnerId)
        },
        onClickNotifications = {
            navController.navigateToNotificationsScreen()
        },
        onClickFriendRequests = {
            navController.navigateToFriendRequests()
        },
        onClickTryAgain = viewModel::onClickTryAgain
    )

}


@Composable
private fun HomeContent(
    state: HomeUiState,
    onClickLike: (Int, Int, Boolean) -> Unit,
    onClickComment: (Int) -> Unit,
    onClickPost: (Int, Int) -> Unit,
    onClickNotifications: () -> Unit,
    onClickFriendRequests: () -> Unit,
    onClickTryAgain: () -> Unit
) {

    val posts = state.posts.collectAsLazyPagingItems()
    val isEmptyFlow = posts.itemSnapshotList.isEmpty()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)

    ) {

        TopBar(
            notificationsCount = state.notificationsCount,
            onClickNotifications = onClickNotifications,
            firendRequestsCount = state.friendRequestsCount,
            onClickFriendRequests = onClickFriendRequests
        )

        if (state.isLoading) {
            LottieLoading()
        } else if (state.isError) {
            LottieError(onClickTryAgain)
        } else {
            if (isEmptyFlow && state.isLoading.not()) {
                ImageForEmptyList(modifier = Modifier
                    .fillMaxSize()
                    .align(alignment = Alignment.CenterHorizontally))
            } else {
                LazyColumn(
                    Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {

                    items(items = posts) {
                        it?.let { post ->
                            ItemPost(
                                post = post,
                                onClickPost = onClickPost,
                                onLike = onClickLike,
                                onComment = onClickComment,
                                onShare = {}
                            )
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



    }

}

