package com.octopus.socialnetwork.ui.screen.comments.uistate

import androidx.paging.PagingData
import com.octopus.socialnetwork.ui.screen.post.uistate.PostUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class CommentsUiState(
   // val comments: List<CommentDetailsUiState> = emptyList(),
    var comments: Flow<PagingData<CommentDetailsUiState>> = emptyFlow(),
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    var comment: String = "",
    val isSent: Boolean = false,
)
