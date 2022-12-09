package com.octopus.socialnetwork.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    private val fetchUserNotificationsCountUseCase: FetchUserNotificationsCountUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HomeUiState())
    val state = _state.asStateFlow()

    private val _notificationsCountState = MutableStateFlow(0)
    val notificationsCountState = _notificationsCountState.asStateFlow()


    init {
        getPosts(16)
    }

    private fun getPosts(currentUserId: Int) {
        viewModelScope.launch {
            try {
                val post = fetchNewsFeedPost(currentUserId).map { it.toPostUiState() }
                val currentNotificationsCount = fetchUserNotificationsCountUseCase(currentUserId, null).notifications
                _state.update { it.copy(
                    posts = post,
                    isLoading = false,
                    isSuccess = true,
                    isError = false
                ) }

                _notificationsCountState.update { oldNotificationsCount ->
                    oldNotificationsCount + currentNotificationsCount
                }

            } catch (e: Exception) {
                _state.update { it.copy(
                    isLoading = false,
                    isSuccess = false,
                    isError = true
                ) }
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