package com.octopus.socialnetwork.ui.screen.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octopus.socialnetwork.domain.usecase.like.LikePostUseCase
import com.octopus.socialnetwork.domain.usecase.like.LikeUseCase
import com.octopus.socialnetwork.domain.usecase.like.UnlikeUseCase
import com.octopus.socialnetwork.domain.usecase.post.FetchNewsFeedPostUseCase
import com.octopus.socialnetwork.ui.screen.home.uistate.HomeUiState
import com.octopus.socialnetwork.ui.screen.post.mapper.toPostUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchNewsFeedPost: FetchNewsFeedPostUseCase,
    private val likePostUseCase: LikePostUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(HomeUiState())
    val state = _state.asStateFlow()


    init {
        getPosts(23)
    }

    private fun getPosts(currentUserId: Int) {
        viewModelScope.launch {
            try {
                val posts = fetchNewsFeedPost(currentUserId).map { it.toPostUiState() }
                _state.update {
                    it.copy(
                        posts = posts,
                        isLoading = false,
                        isSuccess = true,
                        isError = false
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

    fun onClickLike(postId: Int) {
        viewModelScope.launch {
            try {
                val posts = _state.value.posts
                posts.find { it.postId == postId }?.let { post ->
                    likePostUseCase(postId = postId, isLiked = post.isLiked)?.let { newLikesCount ->
                        changePostState(
                            postId = postId,
                            isLiked = post.isLiked.not(),
                            newLikesCount = newLikesCount
                        )
                    }
                }

            } catch (e: Exception) {
                Log.i("TESTING", "failed due to exception ${e}")
                _state.update {
                    it.copy(
                        isLoading = false,
                        isError = true
                    )
                }
            }
        }
    }

    private fun changePostState(postId: Int, newLikesCount: Int, isLiked: Boolean) {

        _state.update { homeUiState ->
            homeUiState.copy(
                posts = _state.value.posts.map { post ->
                    if (post.postId == postId) post.copy(
                        isLiked = isLiked,
                        likeCount = newLikesCount.toString()
                    )
                    else post
                }
            )
        }
    }

    fun onClickComment() {
        //
    }

    fun onClickShare() {
        //
    }


}