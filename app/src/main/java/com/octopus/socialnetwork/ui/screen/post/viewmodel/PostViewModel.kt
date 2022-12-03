package com.octopus.socialnetwork.ui.screen.post.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octopus.socialnetwork.domain.usecase.viewpost.FetchPostDetailsUseCase
import com.octopus.socialnetwork.ui.screen.post.mapper.asPostUiState
import com.octopus.socialnetwork.ui.screen.post.uistate.PostUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class PostViewModel @Inject constructor(
    private val fetchPostDetails: FetchPostDetailsUseCase
) : ViewModel() {

    init {
        getPostDetails(
            postId = 1,
            userId = 1
        )
    }

    private val _state = MutableStateFlow(PostUiState())
    val state = _state.asStateFlow()

    private fun getPostDetails(postId: Int, userId: Int) {
        viewModelScope.launch {
            try {
                val post = fetchPostDetails(postId, userId).asPostUiState()
                _state.update {
                    it.copy(
                        isLoading = false,
                        isSuccess = true,
                        isError = false,
                        userName = post.userName,
                        fullName = post.fullName,
                        profileAvatar = post.profileAvatar,
                        postImage = post.postImage,
                        postDescription = post.postDescription,
                        likeCount = post.likeCount,
                        commentCount = post.commentCount
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