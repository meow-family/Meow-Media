package com.octopus.socialnetwork.ui.screen.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.composable.AppBar
import com.octopus.socialnetwork.ui.composable.SpacerVertical16
import com.octopus.socialnetwork.ui.composable.search.SearchViewItem
import com.octopus.socialnetwork.ui.composable.search.LottieSearch
import com.octopus.socialnetwork.ui.composable.search.SearchItem
import com.octopus.socialnetwork.ui.screen.profile.navigateToUserProfileScreen
import com.octopus.socialnetwork.ui.theme.outLine
import com.octopus.socialnetwork.ui.screen.search.state.SearchUiState

@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    SearchContent(
        state = state,
        onChangeTypingSearch = viewModel::onChangeQuery,
        onClickItem = { navController.navigateToUserProfileScreen(it) }
    )
}


@Composable
private fun SearchContent(
    state: SearchUiState,
    onChangeTypingSearch: (String) -> Unit,
    onClickItem: (Int) -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
    ) {
        AppBar(title = stringResource(R.string.search), showBackButton = false)
        Divider(color = MaterialTheme.colors.outLine, thickness = 1.dp)
        SpacerVertical16()

        SearchViewItem(state = state, onValueChange = onChangeTypingSearch)
        LazyColumn {
            if(state.query.isEmpty()) {
                item { LottieSearch() }
            }
            else {
                items(state.users) { searchItem ->
                    SearchItem(state = searchItem, onClickItem = onClickItem) }
            }
        }

    }
}


