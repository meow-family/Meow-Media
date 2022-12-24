package com.octopus.socialnetwork.ui.screen.post

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octopus.socialnetwork.domain.usecase.like.LikeToggleUseCase
import com.octopus.socialnetwork.domain.usecase.post.FetchPostDetailsUseCase
import com.octopus.socialnetwork.ui.screen.post.mapper.toPostUiState
import com.octopus.socialnetwork.ui.screen.post.uistate.PostMainUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val fetchPostDetails: FetchPostDetailsUseCase,
    private val likeToggleUseCase: LikeToggleUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val args: PostScreenArgs = PostScreenArgs(savedStateHandle)


    init {
        getPostDetails()
    }

    private val _state = MutableStateFlow(PostMainUiState())
    val state = _state.asStateFlow()

    private fun getPostDetails() {
        viewModelScope.launch {
            try {
                val post = fetchPostDetails(args.postId.toInt()).toPostUiState()
                _state.update { it.copy(isLoading = false, isError = false, postDetails = post) }
                Log.i(
                    "TESTING",
                    fetchPostDetails(args.postId.toInt()).toString()
                )
            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false, isError = true) }
            }
        }
    }

    fun onClickLike() {
        viewModelScope.launch {
            try {

                val post = _state.value.postDetails
                toggleLikeState(
                    newLikeState = post.isLiked.not(),
                    newLikesCount = likeToggleUseCase(
                        contentId = post.postId,
                        contentType = "post",
                        isLiked = post.isLiked,
                        totalLikes = post.likeCount.toInt()
                    ) ?: 0
                )
            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false, isError = true) }
            }
        }
    }

    private fun toggleLikeState(newLikesCount: Int, newLikeState: Boolean) {
        _state.update { postUiState ->
            postUiState.copy(
                postDetails = _state.value.postDetails.copy(
                    isLiked = newLikeState,
                    likeCount = newLikesCount.toString()
                )
            )
        }
    }


    fun onClickShare() {
        //
    }

    fun onClickTryAgain() {
        getPostDetails()
    }
}