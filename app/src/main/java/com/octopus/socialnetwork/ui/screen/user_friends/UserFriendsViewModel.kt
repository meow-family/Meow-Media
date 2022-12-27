package com.octopus.socialnetwork.ui.screen.user_friends

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octopus.socialnetwork.domain.usecase.authentication.FetchUserIdUseCase
import com.octopus.socialnetwork.domain.usecase.user.user_details.FetchFriendsUseCase
import com.octopus.socialnetwork.ui.screen.profile.mapper.toUserDetailsUiState
import com.octopus.socialnetwork.ui.screen.user_friends.state.UserFriendsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserFriendsViewModel @Inject constructor(
    private val fetchFriends: FetchFriendsUseCase,
    private val fetchUserId: FetchUserIdUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(UserFriendsUiState())
    val state = _state.asStateFlow()

    init {
        getUserFriends()
    }

   private fun getUserFriends() {
           viewModelScope.launch(Dispatchers.IO) {
                try {
                    val friends = fetchFriends(fetchUserId())
                    _state.update { searchUiState ->
                        searchUiState.copy(
                            users = friends.friends.map { it.toUserDetailsUiState() },
                            isLoading = false,
                            isError = false,
                        )
                    }
                }catch (e: Exception) {
                    _state.update { it.copy(isError = false) }

                }

           }
    }

}