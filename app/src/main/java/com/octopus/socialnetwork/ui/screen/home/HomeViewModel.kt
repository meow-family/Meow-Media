package com.octopus.socialnetwork.ui.screen.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.map
import com.octopus.socialnetwork.domain.usecase.like.LikeToggleUseCase
import com.octopus.socialnetwork.domain.usecase.notifications.FetchUserNotificationsCountUseCase
import com.octopus.socialnetwork.domain.usecase.post.FetchNewsFeedPostUseCase
import com.octopus.socialnetwork.domain.usecase.user.friend_requests.FetchFriendRequestsListUseCase
import com.octopus.socialnetwork.ui.screen.home.uistate.HomeUiState
import com.octopus.socialnetwork.ui.screen.post.mapper.toPostUiState
import com.octopus.socialnetwork.ui.screen.profile.mapper.toUserDetailsUiState
import com.octopus.socialnetwork.ui.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchNewsFeedPost: FetchNewsFeedPostUseCase,
    private val toggleLikeUseCase: LikeToggleUseCase,
    private val fetchNotificationsCount: FetchUserNotificationsCountUseCase,
    private val fetchFriendRequestsListUseCase: FetchFriendRequestsListUseCase,
) : ViewModel() {

    private val _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState = _homeUiState.asStateFlow()


    init {
        getPosts()
        viewModelScope.launch {
            getFriendRequestsCount()
            getNotificationsCount()
        }
    }


    private fun getPosts() {

        val posts =
            Pager(
                PagingConfig(
                    pageSize = Constants.ITEMS_PER_PAGE,
                    enablePlaceholders = true
                )
            )
            { fetchNewsFeedPost() }.flow.cachedIn(viewModelScope).map { pagingData ->
                pagingData.map { post -> post.toPostUiState() }
            }



        _homeUiState.update { it.copy(posts = posts, isLoading = false, isError = false) }

    }

    fun onClickLike(postId: Int) {
        viewModelScope.launch {
            try {
                val posts = _homeUiState.value.posts
                posts.map { pagingData ->
                    pagingData.map { post ->
                        if (post.postId == postId) {
                            toggleLikeState(
                                postId = postId,
                                isLiked = post.isLiked.not(),
                                newLikesCount = toggleLikeUseCase(
                                    contentId = postId,
                                    contentType = "post",
                                    isLiked = post.isLiked
                                ) ?: 0
                            )
                        }
                    }
                }
            } catch (e: Exception) {
                Log.i("TESTING", "failed due to exception ${e}")
                _homeUiState.update { it.copy(isLoading = false, isError = true) }
            }
        }
    }

    private fun toggleLikeState(postId: Int, newLikesCount: Int, isLiked: Boolean) {
        _homeUiState.update { homeUiState ->
            homeUiState.copy(
                posts = _homeUiState.value.posts.map {  pagingData ->
                    pagingData.map{ post ->
                        if (post.postId == postId) post.copy(
                            isLiked = isLiked,
                            likeCount = newLikesCount.toString()
                        )
                        else post
                    }

                }
            )
        }
    }


    fun onClickShare() {
        //
    }

    private suspend fun getFriendRequestsCount() {
        try {
            val friendRequestsCount =
                fetchFriendRequestsListUseCase.invoke().map { it.toUserDetailsUiState() }.size
            _homeUiState.update { it.copy(friendRequestsCount = friendRequestsCount) }
        } catch (e: Exception) {
            _homeUiState.update { it.copy(isError = true) }
        }


    }

    private suspend fun getNotificationsCount() {
        try {
            val currentNotificationsCount = fetchNotificationsCount().notifications
            _homeUiState.update { it.copy(notificationsCount = currentNotificationsCount) }
        } catch (e: Exception) {
            _homeUiState.update { it.copy(isError = false) }
        }
    }
}