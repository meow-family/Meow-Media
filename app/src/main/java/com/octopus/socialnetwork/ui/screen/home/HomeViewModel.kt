package com.octopus.socialnetwork.ui.screen.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octopus.socialnetwork.domain.usecase.like.UpdateLikeUseCase
import com.octopus.socialnetwork.domain.usecase.notifications.FetchUserNotificationsCountUseCase
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
    private val updateLikeUseCase: UpdateLikeUseCase,
    private val fetchNotificationsCount: FetchUserNotificationsCountUseCase
) : ViewModel() {

    private val _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState = _homeUiState.asStateFlow()


    init {
        getPosts(16)
    }

    private fun getPosts(currentUserId: Int) {
        viewModelScope.launch {
            try {
                val posts = fetchNewsFeedPost(currentUserId).map { it.toPostUiState() }
                val currentNotificationsCount = fetchNotificationsCount(currentUserId).notifications
                _homeUiState.update {
                    it.copy(
                        notificationsCount = currentNotificationsCount,
                        posts = posts,
                        isLoading = false,
                        isError = false
                    )
                }
            } catch (e: Exception) {
                _homeUiState.update {
                    it.copy(
                        isLoading = false,
                        isError = true
                    )
                }
            }
        }
    }

    fun onClickLike(postId: Int) {
        viewModelScope.launch {
            try {
                val posts = _homeUiState.value.posts
                posts.find { it.postId == postId }?.let { post ->
                    Log.i("TESTING", "postId $postId")
                    updatePostLikeState(
                        postId = postId,
                        isLiked = post.isLiked.not(),
                        newLikesCount = updateLikeUseCase(postId = postId, isLiked = post.isLiked) ?: 0
                    )
                }
            } catch (e: Exception) {
                Log.i("TESTING", "failed due to exception ${e}")
                _homeUiState.update { it.copy(isLoading = false, isError = true) }
            }
        }
    }

    private fun updatePostLikeState(postId: Int, newLikesCount: Int, isLiked: Boolean) {
        _homeUiState.update { homeUiState ->
            homeUiState.copy(
                posts = _homeUiState.value.posts.map { post ->
                    if (post.postId == postId) post.copy(
                        isLiked = isLiked,
                        likeCount = newLikesCount.toString()
                    )
                    else post
                }
            )
        }
    }


    fun onClickShare() {
        //
    }


}