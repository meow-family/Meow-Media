package com.octopus.socialnetwork.ui.screen.comments

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CommentsViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(CommentsUiState())
    val state = _state.asStateFlow()

    init {
        getFakeData()
    }

    fun getFakeData() {
        _state.update {
            it.copy(
                comments = listOf(
                    CommentDetailsUiState(
                        "Abullah Bashar", "@98abdullah",
                        "https://freepngimg.com/thumb/man/22654-6-man-thumb.png", "nice photo", "12"
                    ),
                    CommentDetailsUiState(
                        "Abullah Bashar", "@98abdullah",
                        "https://freepngimg.com/thumb/man/22654-6-man-thumb.png", "nice photo", "12"
                    ),
                    CommentDetailsUiState(
                        "Abullah Bashar", "@98abdullah",
                        "https://freepngimg.com/thumb/man/22654-6-man-thumb.png", "nice photo", "12"
                    ),
                    CommentDetailsUiState(
                        "Abullah Bashar", "@98abdullah",
                        "https://freepngimg.com/thumb/man/22654-6-man-thumb.png", "nice photo", "12"
                    ),
                    CommentDetailsUiState(
                        "Abullah Bashar", "@98abdullah",
                        "https://freepngimg.com/thumb/man/22654-6-man-thumb.png", "nice photo", "12"
                    ),
                    CommentDetailsUiState(
                        "Abullah Bashar", "@98abdullah",
                        "https://freepngimg.com/thumb/man/22654-6-man-thumb.png", "nice photo", "12"
                    ),
                    CommentDetailsUiState(
                        "Abullah Bashar", "@98abdullah",
                        "https://freepngimg.com/thumb/man/22654-6-man-thumb.png", "nice photo", "12"
                    ),
                    CommentDetailsUiState(
                        "Abullah Bashar", "@98abdullah",
                        "https://freepngimg.com/thumb/man/22654-6-man-thumb.png", "nice photo", "12"
                    ),
                    CommentDetailsUiState(
                        "Abullah Bashar", "@98abdullah",
                        "https://freepngimg.com/thumb/man/22654-6-man-thumb.png", "nice photo", "12"
                    ),
                    CommentDetailsUiState(
                        "Abullah Bashar", "@98abdullah",
                        "https://freepngimg.com/thumb/man/22654-6-man-thumb.png", "nice photo", "12"
                    )
                )
            )
        }
    }
}