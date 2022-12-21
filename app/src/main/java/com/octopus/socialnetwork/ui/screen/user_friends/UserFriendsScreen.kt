package com.octopus.socialnetwork.ui.screen.user_friends

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.octopus.socialnetwork.ui.composable.search.LottieSearch
import com.octopus.socialnetwork.ui.composable.search.SearchItem
import com.octopus.socialnetwork.ui.screen.profile.navigateToUserProfileScreen
import com.octopus.socialnetwork.ui.screen.search.state.SearchUiState

@Composable
fun UserFriendsScreen(
    navController: NavController,
    viewModel: UserFriendsViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    UserFriendsContent(
        state = state,
        onClickItem = { navController.navigateToUserProfileScreen(it) }
    )
}


@Composable
private fun UserFriendsContent(
    state: SearchUiState,
    onClickItem: (Int) -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
    ) {
        LazyColumn {

                items(state.users) { searchItem ->
                    SearchItem(state = searchItem, onClickItem = onClickItem) }

        }

    }
}


