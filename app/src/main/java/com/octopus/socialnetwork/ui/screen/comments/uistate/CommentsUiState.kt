package com.octopus.socialnetwork.ui.screen.comments.uistate

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class CommentsUiState(
   // val comments: List<CommentDetailsUiState> = emptyList(),
    val comments: Flow<PagingData<CommentDetailsUiState>> = emptyFlow(),
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    var comment: String = "",
    val isSent: Boolean = false,
)
