package com.octopus.socialnetwork.ui.screen.home

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.map
import com.octopus.socialnetwork.domain.usecase.like.LikeToggleUseCase
import com.octopus.socialnetwork.domain.usecase.notifications.FetchUserNotificationsCountUseCase
import com.octopus.socialnetwork.domain.usecase.post.FetchNewsFeedPostUseCase
import com.octopus.socialnetwork.domain.usecase.user.friend_requests.FetchFriendRequestsListUseCase
import com.octopus.socialnetwork.ui.screen.home.uistate.HomeUiState
import com.octopus.socialnetwork.ui.screen.post.mapper.toPostUiState
import com.octopus.socialnetwork.ui.screen.post.uistate.PostUiState
import com.octopus.socialnetwork.ui.screen.profile.mapper.toUserDetailsUiState
import com.octopus.socialnetwork.ui.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
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
        getFriendRequestsCount()
        getNotificationsCount()
    }


    private fun getPosts() {
        viewModelScope.launch {
            try {
                val posts = fetchNewsFeedPost().cachedIn(viewModelScope).map { pagingData ->
                    pagingData.map { post -> post.toPostUiState() }
                }
                _homeUiState.update { it.copy(posts = posts, isLoading = false, isError = false) }
                Log.e("TESTING", "getPosts: ${posts}")

            } catch (e: Exception) {
                _homeUiState.update { it.copy(isLoading = false, isError = true) }

            }
        }
    }

    fun onClickLike(postId: Int, isLiked: Boolean) {
        Log.i("TESTING", "on click ${postId} ")
        try {
            viewModelScope.launch {
                _homeUiState.value.posts.collect { pagingData ->
                    pagingData.map { post ->
                        Log.i("TESTING", "onClickLike= post id in collect : ${post.postId } ,isLiked: ${postId} ")
                        (if (post.postId == postId){
                            Log.i("TESTING", "onClickLike= post id in collect : ${post.postId } ,isLiked: ${postId} ")
                            toggleLikeUseCase(postId, isLiked = post.isLiked, contentType = "post")
                        } else Log.i("TESTING", "onClickLike= post id in collect : ${post.postId } ,isLiked: ${postId} "))!!
                    }
                }
                Log.e("TESTING", "onClickLike: ${   _homeUiState.value.posts.collect() }")
            }
            Log.e("TESTING", "try: ")

        } catch (e: Exception) { Log.i("TESTING", "on click ${e.message} ") }
    }


//    fun onClickLike(postId: Int, isLiked: Boolean) {
//        viewModelScope.launch {
//            try {
//                toggleLikeUseCase(
//                    postId,
//                    isLiked = isLiked,
//                    contentType = "post"
//                )
//                getPosts()
//            } catch (e: Exception) {
//                Log.i("TESTING", "onClickLike= post id : $postId ,isLiked: ${isLiked},,, ${e.message} ")
//            }
//        }
//    }


//    private fun toggleLikeState(postId: Int, newLikesCount: Int, isLiked: Boolean) {
//        _homeUiState.update { homeUiState ->
//            homeUiState.copy(
//                posts = _homeUiState.value.posts{ post ->
//                    if (post.postId == postId) post.copy(
//                        isLiked = isLiked,
//                        likeCount = newLikesCount.toString()
//                    )
//                    else post
//                }
//            )
//        }
//    }


    fun onClickShare() {
        //soon
    }

    private fun getFriendRequestsCount() {
        viewModelScope.launch {
            try {
                val friendRequestsCount =
                    fetchFriendRequestsListUseCase().map { it.toUserDetailsUiState() }.size
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
}