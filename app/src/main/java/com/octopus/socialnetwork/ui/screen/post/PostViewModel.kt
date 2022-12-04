package com.octopus.socialnetwork.ui.screen.post

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octopus.socialnetwork.domain.usecase.post.FetchPostDetailsUseCase
import com.octopus.socialnetwork.ui.screen.post.mapper.asPostUiState
import com.octopus.socialnetwork.ui.screen.post.uistate.PostMainUiState
import com.octopus.socialnetwork.ui.screen.post.uistate.PostUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val fetchPostDetails: FetchPostDetailsUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val args: PostScreenArgs= PostScreenArgs(savedStateHandle)
    init {
        getPostDetails(
            postId = 309,
            postOwnerId = 11
        )
    }

    private val _state = MutableStateFlow(PostMainUiState())
    val state = _state.asStateFlow()

    private fun getPostDetails(postId: Int, postOwnerId: Int) {
        viewModelScope.launch {
            try {
                val post = fetchPostDetails(postId, postOwnerId).asPostUiState()
                _state.update {
                    it.copy(
                        isLoading = false,
                        isSuccess = true,
                        isError = false,
                        postDetails = post
                    )
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        isSuccess = false,
                        isError = true
                    )
                }
            }
        }
    }

    fun onClickLike() {
        //
    }

    fun onClickComment() {
        //
    }

    fun onClickShare() {
        //
    }
}