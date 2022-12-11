package com.octopus.socialnetwork.ui.screen.comments

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octopus.socialnetwork.domain.usecase.comments.AddCommentUseCase
import com.octopus.socialnetwork.domain.usecase.comments.GetPostCommentsUseCase
import com.octopus.socialnetwork.domain.usecase.like.UpdateCommentLikeUseCase
import com.octopus.socialnetwork.ui.screen.comments.mapper.toCommentDetailsUiState
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
    private val updateCommentLikeUseCase: UpdateCommentLikeUseCase,
    private val addCommentUseCase: AddCommentUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val args: CommentsScreenArgs = CommentsScreenArgs(savedStateHandle)

    private val _state = MutableStateFlow(CommentsUiState())
    val state = _state.asStateFlow()

    init {
        getPostComments()
    }

    private fun getPostComments() {
        viewModelScope.launch {
            try {
                val postComments = getPostCommentsUseCase(
                    currentUserId = 16,
                    postId = args.postId.toInt(),
                    type = args.type
                ).map { it.toCommentDetailsUiState() }
                _state.update {
                    it.copy(
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
    }

    fun onChangeTypingComment(newValue: String) {
        _state.update {
            it.copy(
                textFieldCommentState = it.textFieldCommentState.copy(text = newValue)

            )
        }
    }

    suspend fun addComment() {
        viewModelScope.launch {
            try {
                addCommentUseCase(324, _state.value.textFieldCommentState.text, 31)
                _state.update {
                    it.copy(
                        textFieldCommentState = it.textFieldCommentState.copy(text = "")
                    )
                }
                getPostComments()
            } catch (e: Throwable) {
                _state.update {
                    it.copy(
                        isError = true
                    )
                }
            }
        }.join()
    }

    fun onClickLike(commentId: Int) {
        viewModelScope.launch {
            try {
                val comment = _state.value.comments
                comment.find { it.commentOwnerId == commentId }?.let { commentO ->
                    Log.i("TESTING", "commentId $commentId")
                    updatePostLikeState(
                        commentId = commentId,
                        isLiked = commentO.isLikedByUser.not(),
                        newLikesCount = updateCommentLikeUseCase(
                            commentId = commentId,
                            isLiked = commentO.isLikedByUser
                        ) ?: 0
                    )
                }
            } catch (e: Exception) {
                Log.i("TESTING", "failed due to exception $e")
                _state.update { it.copy(isLoading = false, isError = true) }
            }
        }
    }

    private fun updatePostLikeState(commentId: Int, newLikesCount: Int, isLiked: Boolean) {
        _state.update { commentUiState ->
            commentUiState.copy(
                comments = _state.value.comments.map { comment ->
                    if (comment.commentOwnerId == commentId) comment.copy(
                        isLikedByUser = isLiked,
                        likeCounter = newLikesCount
                    )
                    else comment
                }
            )
        }
    }
}