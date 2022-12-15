package com.octopus.socialnetwork.ui.screen.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.octopus.socialnetwork.ui.composable.search.SearchField
import com.octopus.socialnetwork.ui.composable.search.SearchItem

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
            .background(color = Color.White),
    ) {

        SearchField(textState)
        LazyColumn {
            item {
                SearchItem()
                SearchItem()
                SearchItem()
            }
        }

    }
}


