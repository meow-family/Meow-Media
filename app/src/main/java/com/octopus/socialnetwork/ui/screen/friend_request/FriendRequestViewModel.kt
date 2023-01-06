package com.octopus.socialnetwork.ui.screen.friend_request

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octopus.socialnetwork.domain.usecase.user.friend_requests.AddFriendUseCase
import com.octopus.socialnetwork.domain.usecase.user.friend_requests.FetchFriendRequestsListUseCase
import com.octopus.socialnetwork.domain.usecase.user.friend_requests.RemoveFriendUseCase
import com.octopus.socialnetwork.ui.screen.friend_request.state.FriendRequestUiState
import com.octopus.socialnetwork.ui.screen.profile.mapper.toUserDetailsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FriendRequestViewModel @Inject constructor(
    private val fetchFriendRequestsList: FetchFriendRequestsListUseCase,
    private val addFriend: AddFriendUseCase,
    private val removeFriend: RemoveFriendUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(FriendRequestUiState())
    val state = _state.asStateFlow()

    init {
        getFriendRequests()
    }

    private fun getFriendRequests() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val friendRequests = fetchFriendRequestsList()

                _state.update {
                    it.copy(
                        requests = friendRequests.map { it.toUserDetailsUiState() },
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
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val isRequestExists = addFriend.invoke(clickedUserId,false).requestExists
                removeRequestIfNotExists(isRequestExists,clickedUserId)
            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false, isError = true) }
            }
        }
    }

    fun onClickDecline(clickedUserId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val isRequestExists = addFriend.invoke(clickedUserId,true).requestExists
                removeRequestIfNotExists(isRequestExists,clickedUserId)
            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false, isError = true) }
            }
        }
    }

    private fun removeRequestIfNotExists(isRequestExist: Boolean, userId: Int) {
        _state.update {
            it.copy(requests = if (!isRequestExist) _state.value.requests.filter { it.userId != userId } else _state.value.requests)
        }
    }

    fun onClickTryAgain() {
        getFriendRequests()
    }
}