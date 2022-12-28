package com.octopus.socialnetwork.ui.screen.profile.state

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octopus.socialnetwork.domain.usecase.authentication.FetchUserIdUseCase
import com.octopus.socialnetwork.domain.usecase.authentication.logout.LogoutUseCase
import com.octopus.socialnetwork.domain.usecase.user.FetchUserPostsUseCase
import com.octopus.socialnetwork.domain.usecase.user.friend_requests.AddFriendUseCase
import com.octopus.socialnetwork.domain.usecase.user.friend_requests.CheckUserIsFriendUseCase
import com.octopus.socialnetwork.domain.usecase.user.friend_requests.RemoveFriendUseCase
import com.octopus.socialnetwork.domain.usecase.user.user_details.FetchUserDetailsUseCase
import com.octopus.socialnetwork.domain.usecase.user.user_details.FetchFriendsUseCase
import com.octopus.socialnetwork.ui.screen.profile.ProfileScreenArgs
import com.octopus.socialnetwork.ui.screen.profile.mapper.toProfilePostsUiState
import com.octopus.socialnetwork.ui.screen.profile.mapper.toUserDetailsUiState
import com.octopus.socialnetwork.ui.screen.profile.state.uistate.ProfileUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val fetchUserDetailS: FetchUserDetailsUseCase,
    private val fetchUserFriendsCount: FetchFriendsUseCase,
    private val fetchUserPosts: FetchUserPostsUseCase,
    private val fetchUserId: FetchUserIdUseCase,
    private val addFriend: AddFriendUseCase,
    private val removeFriend: RemoveFriendUseCase,
    private val checkUserIsFriend: CheckUserIsFriendUseCase,
    private val logout: LogoutUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val args: ProfileScreenArgs = ProfileScreenArgs(savedStateHandle)


    private val _state = MutableStateFlow(ProfileUiState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val myUserId = fetchUserId().first()
            val visitedUserId = args.visitedUserId?.toIntOrNull() ?: myUserId
            _state.update { profile ->

                profile.copy(
                    isMyProfile = myUserId == visitedUserId,
                    userDetails = profile.userDetails.copy(
                        userId = args.visitedUserId?.toIntOrNull() ?: myUserId,
                    )
                )
            }
        }

        getUserDetails(_state.value.userDetails.userId)
        if (_state.value.isMyProfile.not()) {
            isRequestSent(_state.value.userDetails.userId)
        }
    }


    private fun getUserDetails(myUserId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val userFriendsCount = fetchUserFriendsCount(myUserId).total
                val profilePosts = fetchUserPosts(myUserId).posts.toProfilePostsUiState()
                val userPostsCount = fetchUserPosts(myUserId).count
                val profileUiState = fetchUserDetailS(myUserId).toUserDetailsUiState()

                _state.update {
                    it.copy(
                        isLoading = false,
                        isError = false,
                        profilePosts = profilePosts,
                        userDetails = it.userDetails.copy(
                            fullName = profileUiState.fullName,
                            username = profileUiState.username,
                            profileAvatar = profileUiState.profileAvatar,
                            profileCover = profileUiState.profileCover,
                            friendsCount = userFriendsCount.toString(),
                            postCount = userPostsCount.toString(),
                            userId = profileUiState.userId,
                        )
                    )
                }
            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false, isError = true) }
            }
        }
    }

    private fun isRequestSent(visitedUserId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                Log.i("TESTING", "view model isRequestSent $visitedUserId")
                val isRequestSent = checkUserIsFriend(visitedUserId).requestExists
                _state.update { it.copy(isRequestExists = isRequestSent) }
            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false, isError = true) }
            }
        }
    }

    fun onClickAddFriend(friendId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            if (_state.value.isRequestExists.not()) {
                _state.update { it.copy(isRequestExists = true) }
                val result = addFriend(friendId)
                _state.update {
                    it.copy(
                        isRequestExists = result.requestExists,
                        isFriend = result.isFriend
                    )
                }
            } else {
                _state.update { it.copy(isFriend = false) }
                val result = removeFriend(friendId)
                _state.update {
                    it.copy(
                        isRequestExists = result.requestExists,
                        isFriend = result.isFriend
                    )
                }
            }

        }
    }

    fun onClickLogout() {
        viewModelScope.launch(Dispatchers.IO) {
            logout()
            _state.update { it.copy(isLogout = true) }
        }
    }

    fun onClickTryAgain() {
        getUserDetails(_state.value.userDetails.userId)
        if (_state.value.isMyProfile.not()) {
            isRequestSent(_state.value.userDetails.userId)
        }
    }
}