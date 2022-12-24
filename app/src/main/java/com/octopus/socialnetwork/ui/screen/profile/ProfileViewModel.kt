package com.octopus.socialnetwork.ui.screen.profile

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octopus.socialnetwork.domain.usecase.authentication.LogoutUseCase
import com.octopus.socialnetwork.domain.usecase.user.friend_requests.AddFriendUseCase
import com.octopus.socialnetwork.domain.usecase.user.friend_requests.CheckUserFriendUseCase
import com.octopus.socialnetwork.domain.usecase.user.FetchUserDetailsUseCase
import com.octopus.socialnetwork.domain.usecase.user.FetchUserFriendsUseCase
import com.octopus.socialnetwork.domain.usecase.user.FetchUserIdUseCase
import com.octopus.socialnetwork.domain.usecase.user.FetchUserPostsUseCase
import com.octopus.socialnetwork.domain.usecase.user.friend_requests.AddFriendUseCase
import com.octopus.socialnetwork.domain.usecase.user.friend_requests.CheckUserFriendUseCase
import com.octopus.socialnetwork.domain.usecase.user.friend_requests.RemoveFriendUseCase
import com.octopus.socialnetwork.ui.screen.profile.mapper.toProfilePostsUiState
import com.octopus.socialnetwork.ui.screen.profile.mapper.toUserDetailsUiState
import com.octopus.socialnetwork.ui.screen.profile.uistate.ProfileUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val fetchUserDetailS: FetchUserDetailsUseCase,
    private val fetchUserFriendsCount: FetchUserFriendsUseCase,
    private val fetchUserPosts: FetchUserPostsUseCase,
    private val fetchUserId: FetchUserIdUseCase,
    private val addFriendUseCase: AddFriendUseCase,
    private val removeFriendUseCase: RemoveFriendUseCase,
    private val checkUserFriendUseCase: CheckUserFriendUseCase,
    private val logoutUseCase: LogoutUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val args: ProfileScreenArgs = ProfileScreenArgs(savedStateHandle)

    private val myUserId = fetchUserId()
    private val visitedUserId = args.visitedUserId?.toIntOrNull() ?: myUserId

    private val _state = MutableStateFlow(ProfileUiState())
    val state = _state.asStateFlow()

    init {
        updateProfileUiState()
        getUserDetails(visitedUserId)
        isRequestSent(visitedUserId)
    }


    private fun updateProfileUiState() {
        _state.update { it.copy(isMyProfile = visitedUserId == myUserId) }
    }

    private fun getUserDetails(currentUserId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val userFriendsCount = fetchUserFriendsCount(currentUserId).total
                val profilePosts = fetchUserPosts(currentUserId).posts.toProfilePostsUiState()
                val userPostsCount = fetchUserPosts(currentUserId).count
                val profileUiState = fetchUserDetailS(currentUserId).toUserDetailsUiState()

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
                val isRequestSent = checkUserFriendUseCase(visitedUserId).requestExists
                _state.update { it.copy(isRequestExists = isRequestSent) }
            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false, isError = true) }
            }
        }
    }

    fun onClickAddFriend(otherUserId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            if (_state.value.isRequestExists.not()) {
                _state.update { it.copy(isRequestExists = true) }
                val result = addFriendUseCase(otherUserId)
                _state.update {
                    it.copy(
                        isRequestExists = result.requestExists,
                        isFriend = result.isFriend
                    )
                }
            } else {
                _state.update { it.copy(isFriend = false) }
                val result = removeFriendUseCase(otherUserId)
                _state.update {
                    it.copy(
                        isRequestExists = result.requestExists,
                        isFriend = result.isFriend
                    )
                }
            }

        }
    }


    fun onClickMessage(){
        //
    }

    fun onClickLogout() {
        viewModelScope.launch(Dispatchers.IO) {
            logoutUseCase()
            _state.update { it.copy(isLogout = true) }
        }
    }

    fun onClickEditeProfile() {
        //
    }
}