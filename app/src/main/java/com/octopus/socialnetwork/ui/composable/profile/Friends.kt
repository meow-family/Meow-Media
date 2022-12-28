package com.octopus.socialnetwork.ui.composable.profile

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.octopus.socialnetwork.ui.composable.friend_requests.UserDetailsItem
import com.octopus.socialnetwork.ui.screen.profile.uistate.UserDetailsUiState

@Composable
fun Friends(
    state: List<UserDetailsUiState>,
    onClickItem: (Int) -> Unit,
) {
    LazyColumn {
        items(state) { user ->
            UserDetailsItem(state = user, onClickItem = onClickItem)
        }
    }
}