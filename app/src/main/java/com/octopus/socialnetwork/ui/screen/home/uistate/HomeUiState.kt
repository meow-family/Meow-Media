package com.octopus.socialnetwork.ui.screen.home.uistate

import androidx.paging.PagingData
import com.octopus.socialnetwork.ui.screen.post.uistate.PostUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class HomeUiState(
    var posts: Flow<PagingData<PostUiState>> = emptyFlow(),
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val isLiked: Boolean = false,
    val notificationsCount: Int = 0,
    val friendRequestsCount: Int = 0,
)