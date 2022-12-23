package com.octopus.socialnetwork.ui.screen.friend_request

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octopus.socialnetwork.domain.usecase.user.friend_requests.AddFriendUseCase
import com.octopus.socialnetwork.domain.usecase.user.friend_requests.FetchFriendRequestsListUseCase
import com.octopus.socialnetwork.domain.usecase.user.friend_requests.RemoveFriendUseCase
import com.octopus.socialnetwork.ui.screen.friend_request.state.FriendRequestUiState
import com.octopus.socialnetwork.ui.screen.profile.mapper.toUserDetailsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FriendRequestViewModel @Inject constructor(
    private val fetchFriendRequestsListUseCase: FetchFriendRequestsListUseCase,
    private val addFriendUseCase: AddFriendUseCase,
    private val removeFriendUseCase: RemoveFriendUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _state = MutableStateFlow(FriendRequestUiState())
    val state = _state.asStateFlow()

    init {
        getFriendRequests()
    }

    private fun getFriendRequests() {
        viewModelScope.launch {
            try {
                val friendRequests = fetchFriendRequestsListUseCase()

                _state.update {
                    it.copy(
                        friendRequests = friendRequests.map { it.toUserDetailsUiState() },
                        isLoading = false,
                        isError = false,
                    )
                }
            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false, isError = true) }
            }
        }
    }

    fun onClickAccept(clickedUserId: Int) {
        viewModelScope.launch {
            try {
                val isRequestExists = addFriendUseCase.invoke(clickedUserId).requestExists
                removeRequestIfNotExists(isRequestExists,clickedUserId)
            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false, isError = true) }
            }
        }
    }

    fun onClickDecline(clickedUserId: Int) {
        viewModelScope.launch {
            try {
                val isRequestExists = removeFriendUseCase.invoke(clickedUserId).requestExists
                removeRequestIfNotExists(isRequestExists,clickedUserId)
            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false, isError = true) }
            }
        }
    }

    private fun removeRequestIfNotExists(isRequestExist: Boolean, userId: Int) {
        _state.update {
            it.copy(friendRequests = if (!isRequestExist) _state.value.friendRequests.filter { it.userId != userId } else _state.value.friendRequests)
        }
    }

    fun onClickTryAgain() {
        getFriendRequests()
    }
}