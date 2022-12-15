package com.octopus.socialnetwork.ui.screen.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.composable.AppBar
import com.octopus.socialnetwork.ui.composable.search.SearchField
import com.octopus.socialnetwork.ui.composable.search.SearchItem
import com.octopus.socialnetwork.ui.theme.dividerColor

@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel(),
) {

    val state by viewModel.state.collectAsState()
    SearchContent(
    )
}


@Composable
private fun SearchContent(
) {
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
    ) {

        AppBar(title = stringResource(R.string.search), showBackButton = false)
        Divider(color = MaterialTheme.colors.dividerColor, thickness = 1.dp)

        SearchField(textState)
        LazyColumn {
            item {
                SearchItem()
            }
        }

    }
}


