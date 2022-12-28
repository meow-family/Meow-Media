package com.octopus.socialnetwork.ui.screen.comments.state

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octopus.socialnetwork.domain.usecase.comments.AddCommentUseCase
import com.octopus.socialnetwork.domain.usecase.comments.GetPostCommentsUseCase
import com.octopus.socialnetwork.domain.usecase.like.ToggleLikeUseCase
import com.octopus.socialnetwork.ui.screen.comments.CommentsScreenArgs
import com.octopus.socialnetwork.ui.screen.comments.mapper.toCommentDetailsUiState
import com.octopus.socialnetwork.ui.screen.comments.state.uistate.CommentsUiState
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
        getPostComments()
    }

    private fun getPostComments() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val postComments = getPostComments(
                    postId = args.postId.toInt(),
                    type = args.type
                ).map { it.toCommentDetailsUiState() }
                _state.update {
                    it.copy(
                        comments = postComments,
                        isLoading = false,
                        isSent = true,
                        isError = false
                    )
                }
            } catch (e: Throwable) {
                _state.update { it.copy(isLoading = false, isSent = false, isError = true) }
            }
        }
    }

    fun onChangeTypingComment(newValue: String) {
        _state.update { it.copy(comment = newValue) }
    }

    private fun addComment(comment: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                addComment(args.postId.toInt(), comment)
                _state.update { it.copy(comment = it.comment, isSent = true) }
                getPostComments()
            } catch (e: Throwable) {
                _state.update { it.copy(isError = true) }
            }
        }
    }

    fun onClickSend() {
        addComment(_state.value.comment)
        _state.update { it.copy(comment = "") }
    }

    fun onClickLike(commentId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val clickedComment = _state.value.comments
                clickedComment.find { it.commentId == commentId }?.let { comment ->
                    toggleLikeState(
                        commentId = commentId,
                        isLiked = comment.isLikedByUser.not(),
                        newLikesCount = likeToggle(
                            contentId = commentId,
                            isLiked = comment.isLikedByUser,
                            contentType = Constants.LIKE_TYPE,
                            totalLikes = comment.likeCounter
                        ) ?: 0
                    )
                }
            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false, isError = true) }
            }
        }
    }


    private fun toggleLikeState(commentId: Int, newLikesCount: Int, isLiked: Boolean) {
        _state.update { commentUiState ->
            commentUiState.copy(
                comments = _state.value.comments.map { comment ->
                    if (comment.commentId == commentId) {
                        comment.copy(isLikedByUser = isLiked, likeCounter = newLikesCount)
                    } else {
                        comment
                    }
                }
            )
        }
    }

    fun onClickTryAgain() {
        getPostComments()
    }
}