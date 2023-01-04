package com.octopus.socialnetwork.ui.screen.comments

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octopus.socialnetwork.domain.usecase.comments.AddCommentUseCase
import com.octopus.socialnetwork.domain.usecase.comments.GetPostCommentsUseCase
import com.octopus.socialnetwork.domain.usecase.like.ToggleLikeUseCase
import com.octopus.socialnetwork.ui.screen.comments.mapper.toCommentDetailsUiState
import com.octopus.socialnetwork.ui.screen.comments.state.CommentsUiState
import com.octopus.socialnetwork.ui.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommentsViewModel @Inject constructor(
    private val getPostComments: GetPostCommentsUseCase,
    private val likeToggle: ToggleLikeUseCase,
    private val addComment: AddCommentUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val args: CommentsScreenArgs = CommentsScreenArgs(savedStateHandle)

    private val _state = MutableStateFlow(CommentsUiState())
    val state = _state.asStateFlow()

    init {
        _state.update { it.copy(error = "", isLoading = true) }
        getPostComments()
    }

    private fun getPostComments() {
        _state.update { it.copy(isLoading = true, error = "") }
        viewModelScope.launch(Dispatchers.IO) {
            try {
                getPostComments(args.postId.toInt()).map { it.toCommentDetailsUiState() }
                swipeToRefresh()
            } catch (t: Throwable) {
                _state.update { it.copy(isError = true) }
            }
        }
    }

    fun swipeToRefresh() {
        viewModelScope.launch {
            _state.update { it.copy(isPagerLoading = true, pagerError = "") }
            try {
                val postComments =
                    getPostComments(args.postId.toInt()).map { it.toCommentDetailsUiState() }
                        .reversed()
                Log.e("COMEENT", "relly COMEENT :${postComments}")
                _state.update {
                    it.copy(
                        isPagerLoading = false, isLoading = false,
                        isEndOfPager = postComments.isEmpty(),
                        comments = (it.comments + postComments.distinctBy { it.commentId }),
                    )
                }
                Log.e("COMEENT", "SHAFALID COMEENT :${_state.value.comments}")


            } catch (t: Throwable) {
                _state.update {
                    it.copy(
                        isPagerLoading = false, isLoading = false,
                        pagerError = if (_state.value.comments.isNotEmpty()) t.message.toString() else "",
                        error = if (_state.value.comments.isEmpty()) {
                            t.message.toString()
                        } else "",

                        )
                }
            }
        }
    }


    fun onChangeTypingComment(newValue: String) {
        _state.update { it.copy(comment = newValue) }
    }

    fun onClickSend() {
        addComment(_state.value.comment)
        _state.update { it.copy(comment = "") }
    }

    private fun addComment(comment: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val comments = addComment(args.postId.toInt(), comment).toCommentDetailsUiState()
                _state.update { it.copy(comments = it.comments + comments, isSent = true) }

            } catch (e: Throwable) {
                _state.update { it.copy(isError = true) }
            }
        }
    }


    fun onClickLike(postId: Int, totalLikes: Int, isLiked: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                likeToggle(postId, totalLikes, isLiked, Constants.LIKE_TYPE)
                _state.update { it.copy(isError = false) }
            } catch (e: Exception) {
                _state.update { it.copy(isError = true) }
            }
        }
    }

    fun onClickTryAgain() {
        getPostComments()
    }
}