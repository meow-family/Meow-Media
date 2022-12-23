package com.octopus.socialnetwork.ui.screen.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.octopus.socialnetwork.domain.usecase.like.LikeToggleUseCase
import com.octopus.socialnetwork.domain.usecase.notifications.FetchUserNotificationsCountUseCase
import com.octopus.socialnetwork.domain.usecase.post.FetchPostsUseCase
import com.octopus.socialnetwork.domain.usecase.user.FetchUserIdUseCase
import com.octopus.socialnetwork.domain.usecase.user.friend_requests.FetchFriendRequestsListUseCase
import com.octopus.socialnetwork.ui.screen.home.uistate.HomeUiState
import com.octopus.socialnetwork.ui.screen.post.mapper.toPostUiState
import com.octopus.socialnetwork.ui.screen.profile.mapper.toUserDetailsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val toggleLikeUseCase: LikeToggleUseCase,
    private val fetchNotificationsCount: FetchUserNotificationsCountUseCase,
    private val fetchFriendRequestsListUseCase: FetchFriendRequestsListUseCase,
    private val fetchNewsFeed: FetchPostsUseCase,
    private val fetchUserId: FetchUserIdUseCase,
) : ViewModel() {

    private val _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState = _homeUiState.asStateFlow()

    init {
        getNewsFeed()
        getFriendRequestsCount()
        getNotificationsCount()
    }

    private fun getNewsFeed() {
        viewModelScope.launch {
            try {
                val userId = fetchUserId()
                Log.d("MALT", "USER ID IS: $userId")
                val posts = fetchNewsFeed().cachedIn(viewModelScope).map { pagingData ->
                    pagingData.map { post -> post.toPostUiState() }
                }
                _homeUiState.update {
                    it.copy(
                        posts = posts,
                        isLoading = false,
                        isSuccessful = true,
                        isError = false,
                    )
                }
                Log.d("MALT", "LOAD PAGING DATA SUCCESS WITH POSTS: $posts")
            } catch (e: Exception) {
                _homeUiState.update {
                    it.copy(isLoading = false, isSuccessful = false, isError = true,)
                }
                Log.d("MALT", "LOAD PAGING DATA FAILED DUE TO: $e ~ ${e.message}")
            }
        }
    }

    fun onClickLike(postId: Int, totalLikes: Int, isLiked: Boolean) {
        viewModelScope.launch {
            try {
                toggleLikeUseCase(postId, totalLikes, isLiked,"post")
                Log.d("MALT", "POST LIKE STATE CHANGED SUCCESSFULLY: LIKED -> $isLiked | UNLIKED -> $isLiked")
            } catch (e: Exception) {
                Log.d("MALT", "SOMETHING WENT WRONG WHILE TRYING CHANGING POST LIKE STATE: $e ~ ${e.message}")
            }
        }
    }

    fun onClickShare() {
        // soon
    }

    private fun getFriendRequestsCount() {
        viewModelScope.launch {
            try {
                val friendRequestsCount = fetchFriendRequestsListUseCase.invoke().map { it.toUserDetailsUiState() }.size
                _homeUiState.update { it.copy(friendRequestsCount = friendRequestsCount) }
            } catch (e: Exception) {
                _homeUiState.update { it.copy(isError = true) }
            }
        }
    }

    private fun getNotificationsCount() {
        viewModelScope.launch {
            try {
                val currentNotificationsCount = fetchNotificationsCount().notifications
                _homeUiState.update { it.copy(notificationsCount = currentNotificationsCount) }
            } catch (e: Exception) {
                _homeUiState.update { it.copy(isError = false) }
            }
        }
    }

    fun onClickTryAgain() {
        getNewsFeed()
        getFriendRequestsCount()
        getNotificationsCount()
    }
}