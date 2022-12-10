package com.octopus.socialnetwork.ui.screen.post

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octopus.socialnetwork.domain.usecase.like.LikeUseCase
import com.octopus.socialnetwork.domain.usecase.like.UnlikeUseCase
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
    private val likeUseCase: LikeUseCase,
    private val unlikeUseCase: UnlikeUseCase,
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
                val post =
                    fetchPostDetails(args.postId.toInt(), args.postOwnerId.toInt()).toPostUiState()
                _state.update {
                    it.copy(
                        isLoading = false,
                        isError = false,
                        postDetails = post
                    )
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        isError = true
                    )
                }
            }
        }
    }

    fun onClickLike() {
        viewModelScope.launch {
            try {

                _state.value.postDetails.let { post ->

                    if (!post.isLiked) {
                        likeUseCase(userId = 16, postId = post.postId, typeContent = "post")
                    } else {
                        unlikeUseCase(userId = 16, postId = post.postId, typeContent = "post")
                    }

                    _state.update {
                        it.copy(
                            isLoading = false,
                            isError = false
                        )
                    }

                    getPostDetails()

                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        isError = true
                    )
                }
            }
        }
    }

    fun onClickComment() {
        //
    }

    fun onClickShare() {
        //
    }
}