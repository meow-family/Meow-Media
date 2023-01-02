package com.octopus.socialnetwork.ui.screen.comments.state

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class CommentsUiState(
    val comments: List<CommentDetailsUiState> = emptyList(),
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    var comment: String = "",
    val isSent: Boolean = false,
    val isPagerLoading: Boolean = false,
    val isEndOfPager: Boolean = false,
    val error: String = "",
    val pagerError: String = "",
)
