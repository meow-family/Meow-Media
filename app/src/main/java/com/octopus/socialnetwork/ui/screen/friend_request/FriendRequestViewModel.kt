package com.octopus.socialnetwork.ui.screen.friend_request

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octopus.socialnetwork.domain.usecase.user.friend_requests.GetFriendRequestsListUseCase
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
    private val getFriendRequestsListUseCase: GetFriendRequestsListUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _state = MutableStateFlow(FriendRequestUiState())
    val state = _state.asStateFlow()

    init {
        getFriendRequests()
    }

    private fun getFriendRequests() {
        try {
            viewModelScope.launch {
                val friendRequests =
                    getFriendRequestsListUseCase()

                _state.update {
                    it.copy(
                        friendRequests = friendRequests.map { it.toUserDetailsUiState() },
                        isLoading = false,
                        isError = false,
                    )
                }

            }
        } catch (e: Exception) {
            _state.update {
                it.copy(isLoading = false, isError = true)
            }
        }
    }
}