package com.octopus.socialnetwork.ui.screen.post

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octopus.socialnetwork.domain.usecase.post.FetchPostDetailsUseCase
import com.octopus.socialnetwork.ui.navigate.PostScreenArgs
import com.octopus.socialnetwork.ui.screen.post.mapper.asPostUiState
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
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = MutableStateFlow(PostMainUiState())
    val state = _state.asStateFlow()

    private val args: PostScreenArgs = PostScreenArgs(savedStateHandle)

    init {
        getPostDetails()
    }

    private fun getPostDetails() {
        viewModelScope.launch {
            try {
                val post = fetchPostDetails(args.postId.toInt(), args.postOwnerId.toInt()).asPostUiState()
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
        //
    }

    fun onClickComment() {
        //
    }

    fun onClickShare() {
        //
    }
}