package com.octopus.socialnetwork.ui.screen.user_friends

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octopus.socialnetwork.domain.usecase.user.FetchUserFriendsUseCase
import com.octopus.socialnetwork.ui.screen.profile.mapper.toUserDetailsUiState
import com.octopus.socialnetwork.ui.screen.search.state.SearchUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserFriendsViewModel @Inject constructor(
    private val fetchUserFriendsUseCase: FetchUserFriendsUseCase,
    stateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(SearchUiState())
    val state = _state.asStateFlow()

    init {
        val userId = 2
        getUserFriends(2)
    }

   private fun getUserFriends(userId: Int) {
           viewModelScope.launch {
                try {
                    val search = fetchUserFriendsUseCase( userId)
                    _state.update { searchUiState ->
                        searchUiState.copy(
                            users = search.friends.map { it.toUserDetailsUiState() },
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