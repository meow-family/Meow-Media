package com.octopus.socialnetwork.ui.screen.profile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.map
import com.octopus.socialnetwork.domain.usecase.authentication.FetchUserIdUseCase
import com.octopus.socialnetwork.domain.usecase.authentication.logout.LogoutUseCase
import com.octopus.socialnetwork.domain.usecase.user.FetchPostsCountUseCase
import com.octopus.socialnetwork.domain.usecase.user.FetchPostsUseCase
import com.octopus.socialnetwork.domain.usecase.user.UserRelationUseCase
import com.octopus.socialnetwork.domain.usecase.user.friend_requests.AddFriendUseCase
import com.octopus.socialnetwork.domain.usecase.user.friend_requests.CheckUserIsFriendUseCase
import com.octopus.socialnetwork.domain.usecase.user.friend_requests.RemoveFriendUseCase
import com.octopus.socialnetwork.domain.usecase.user.user_details.FetchFriendsUseCase
import com.octopus.socialnetwork.domain.usecase.user.user_details.FetchMyProfileDetailsUseCase
import com.octopus.socialnetwork.domain.usecase.user.user_details.FetchUserDetailsUseCase
import com.octopus.socialnetwork.domain.usecase.user.user_details.InsertMyProfileDetailsLocallyUseCase
import com.octopus.socialnetwork.ui.screen.profile.mapper.toProfilePostUiState
import com.octopus.socialnetwork.ui.screen.profile.mapper.toUserDetailsUiState
import com.octopus.socialnetwork.ui.screen.profile.mapper.toUserRelationUiState
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
    private val userRelation: UserRelationUseCase,
    private val fetchPostsCount: FetchPostsCountUseCase,
    private val fetchPosts: FetchPostsUseCase,
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
        chickIfMyProfile()
    }

    private fun chickIfMyProfile() {
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
            if (_state.value.isMyProfile) {
                getUserDetails()
            } else {
                getFriendDetails()
            }
            if (_state.value.isMyProfile.not()) {
                isRequestSent(_state.value.userDetails.userId)
            }
        }
    }


    private fun getUserDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                insertUserDetailsLocally()
                getFriends()
                getPosts()
                fetchUserDetails().map {
                    it.toUserDetailsUiState()
                }.collect { user ->
                    updateDetails(user)
                }
            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false, isError = true) }
            }
        }
    }

    private fun getFriendDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                getUserRelation()
                getFriends()
                getPosts()
                val user =
                    fetchFriendDetails(_state.value.userDetails.userId).toUserDetailsUiState()
                updateDetails(user)
            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false, isError = true) }
            }
        }
    }

    private suspend fun getUserRelation() {
        val userRelation = userRelation(_state.value.userDetails.userId).toUserRelationUiState()
        _state.update { user ->
            user.copy(
                userDetails = user.userDetails.copy(
                    relation = userRelation
                )
            )
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

    private fun getFriends() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val friend = fetchFriends(_state.value.userDetails.userId)
                _state.update {
                    it.copy(
                        isLoading = false, isError = false,
                        friends = friend.friends.map { it.toUserDetailsUiState() },
                        userDetails = it.userDetails.copy(friendsCount = friend.total.toString())
                    )
                }
            } catch (e: Throwable) {
                _state.update { it.copy(isLoading = false, isError = true) }
            }
        }
    }

    private fun getPosts() {
        viewModelScope.launch(Dispatchers.IO) {

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
    }

    private fun isRequestSent(visitedUserId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
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
        if (_state.value.isMyProfile) getUserDetails() else getFriendDetails()
        if (_state.value.isMyProfile.not()) {
            isRequestSent(_state.value.userDetails.userId)
        }
    }
}