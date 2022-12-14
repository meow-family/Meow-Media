package com.octopus.socialnetwork.ui.screen.friend_request

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octopus.socialnetwork.SocialNetworkApplication.Companion.userId
import com.octopus.socialnetwork.domain.usecase.user.FriendRequestUseCase
import com.octopus.socialnetwork.ui.screen.friend_request.state.FriendRequestUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FriendRequestViewModel @Inject constructor(
    private val friendRequestUseCase: FriendRequestUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _state = MutableStateFlow(FriendRequestUiState())
    val state = _state.asStateFlow()

    init {
        getNotifications()
    }

    private fun getNotifications() {
        try {
            viewModelScope.launch {
                val friendRequests =
                    friendRequestUseCase(userId, 4)

//                _state.update {
//                    it.copy(
//                        friendRequests = friendRequests,
//                        isLoading = false,
//                        isError = false,
//                    )
//                }

            }
        } catch (e: Exception) {
            _state.update {
                it.copy(
                    isLoading = false,
                    isError = true
                )
            }
        }
    }
}