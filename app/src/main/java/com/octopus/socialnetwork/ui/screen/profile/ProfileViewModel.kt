package com.octopus.socialnetwork.ui.screen.profile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.map
import com.octopus.socialnetwork.domain.usecase.authentication.FetchUserIdUseCase
import com.octopus.socialnetwork.domain.usecase.authentication.logout.LogoutUseCase
import com.octopus.socialnetwork.domain.usecase.user.FetchPostsCountUseCase
import com.octopus.socialnetwork.domain.usecase.user.FetchPostsUseCase
import com.octopus.socialnetwork.domain.usecase.user.friend_requests.CheckUserIsFriendUseCase
import com.octopus.socialnetwork.domain.usecase.user.friend_requests.ToggleFriendshipUseCase
import com.octopus.socialnetwork.domain.usecase.user.user_details.FetchFriendsUseCase
import com.octopus.socialnetwork.domain.usecase.user.user_details.FetchMyProfileDetailsUseCase
import com.octopus.socialnetwork.domain.usecase.user.user_details.FetchUserDetailsUseCase
import com.octopus.socialnetwork.domain.usecase.user.user_details.InsertMyProfileDetailsLocallyUseCase
import com.octopus.socialnetwork.ui.screen.profile.mapper.toProfilePostUiState
import com.octopus.socialnetwork.ui.screen.profile.mapper.toUserDetailsUiState
import com.octopus.socialnetwork.ui.screen.profile.state.ProfileUiState
import com.octopus.socialnetwork.ui.screen.profile.state.UserDetailsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val fetchFriendDetails: FetchUserDetailsUseCase,
    private val fetchUserDetails: FetchMyProfileDetailsUseCase,
    private val insertUserDetailsLocally: InsertMyProfileDetailsLocallyUseCase,
    private val fetchFriends: FetchFriendsUseCase,
    private val fetchPostsCount: FetchPostsCountUseCase,
    private val fetchPosts: FetchPostsUseCase,
    private val fetchUserId: FetchUserIdUseCase,
    private val toggleFriendship: ToggleFriendshipUseCase,
    private val checkUserIsFriend: CheckUserIsFriendUseCase,
    private val logout: LogoutUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val args: ProfileScreenArgs = ProfileScreenArgs(savedStateHandle)

    private val _state = MutableStateFlow(ProfileUiState())
    val state = _state.asStateFlow()

    init {
        updateProfileUiState()
        getUserDetails()
    }

    private fun updateProfileUiState() {
        viewModelScope.launch {
            val myUserId = fetchUserId()
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
    }


    private fun getUserDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (_state.value.isMyProfile) {
                    insertUserDetailsLocally()
                }
                getFriends()
                getPosts()
                if (_state.value.isMyProfile) {
                    fetchUserDetails().map { it.toUserDetailsUiState() }.collect { user ->
                        updateDetails(user)
                    }
                } else {
                    val user =
                        fetchFriendDetails(_state.value.userDetails.userId).toUserDetailsUiState()
                    updateDetails(user)
                    isRequestSent(_state.value.userDetails.userId)
                }
                _state.update { it.copy(isLoading = false, isError = false) }

            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false, isError = true) }
            }
        }
    }


    private fun updateDetails(user: UserDetailsUiState) {
        _state.update { profileUiState ->
            profileUiState.copy(
                userDetails = profileUiState.userDetails.copy(
                    fullName = user.fullName, username = user.username,
                    profileAvatar = user.profileAvatar, profileCover = user.profileCover,
                    userId = user.userId,
                )
            )
        }
    }

    private suspend fun getFriends() {
        try {
            val friend = fetchFriends(_state.value.userDetails.userId)
            _state.update {
                it.copy(
                    isError = false,
                    friends = friend.friends.map { it.toUserDetailsUiState() },
                    userDetails = it.userDetails.copy(friendsCount = friend.total.toString())
                )
            }
        } catch (e: Throwable) {
            _state.update { it.copy(isLoading = false, isError = true) }
        }

    }

    private suspend fun getPosts() {

        try {
            val posts = fetchPosts(_state.value.userDetails.userId).map { pager ->
                pager.map { it.toProfilePostUiState() }
            }
            val postsCount = fetchPostsCount(_state.value.userDetails.userId)
            _state.update {
                it.copy(
                    isLoading = false, isError = false,
                    profilePosts = posts,
                    userDetails = it.userDetails.copy(postCount = postsCount.toString())
                )
            }
        } catch (e: Throwable) {
            _state.update { it.copy(isLoading = false, isError = true) }
        }
    }

    private suspend fun isRequestSent(visitedUserId: Int) {
            try {
                val friendshipState = checkUserIsFriend(visitedUserId)
                _state.update {
                    it.copy(
                        isRequestExists = friendshipState.requestExists,
                        isFriend = friendshipState.isFriend
                    )
                }
            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false, isError = true) }
            }

    }

    fun onClickAddFriend(friendId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update {
                it.copy(
                    isRequestExists = _state.value.isRequestExists.not(),
                    isFriend = _state.value.isFriend.not(),
                )
            }
            val result = toggleFriendship(
                friendId,
                _state.value.isFriend.not() || _state.value.isRequestExists.not()
            )
            _state.update {
                it.copy(isFriend = result.isFriend, isRequestExists = result.requestExists)
            }
        }
    }

    fun onClickLogout() {
        viewModelScope.launch(Dispatchers.IO) {
            logout()
        }
    }

    fun onClickTryAgain() {
        getUserDetails()
    }
}