package com.octopus.socialnetwork.ui.screen.comments

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octopus.socialnetwork.domain.usecase.comments.GetPostCommentsUseCase
import com.octopus.socialnetwork.ui.screen.comments.mapper.asCommentDetailsUiState
import com.octopus.socialnetwork.ui.screen.comments.uistate.CommentsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommentsViewModel @Inject constructor(
    private val getPostCommentsUseCase: GetPostCommentsUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(CommentsUiState())
    val state = _state.asStateFlow()

    init {
        getPostComments()
    }

    private fun getPostComments() {
        viewModelScope.launch {
            try {
                val postComments = getPostCommentsUseCase(
                    currentUserId = 31,
                    postId = 324,
                    type = "post"
                ).map { it.asCommentDetailsUiState() }
                _state.update {
                    it.copy(
                        isSuccess = true,
                        comments = postComments
                    )
                }

            } catch (e: Throwable) {
                _state.update {
                    it.copy(
                        isError = true,
                    )
                }
            }
        }

        fun onChangeTypingComment(newValue: String) {
            _state.update {
                it.copy(
                    textFieldCommentState = it.textFieldCommentState.copy(text = newValue)
                )
            }
        }

    }
}