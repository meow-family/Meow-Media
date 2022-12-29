package com.octopus.socialnetwork.ui.screen.profile

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
import com.octopus.socialnetwork.domain.usecase.user.user_details.FetchProfileDetailsUseCase
import com.octopus.socialnetwork.domain.usecase.user.user_details.InsertProfileDetailsUseCase
import com.octopus.socialnetwork.ui.screen.profile.mapper.toProfilePostsUiState
import com.octopus.socialnetwork.ui.screen.profile.mapper.toUserDetailsUiState
import com.octopus.socialnetwork.ui.screen.profile.state.ProfileUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
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
    private val insertProfileDetailsUseCase: InsertProfileDetailsUseCase,
    private val fetchProfileDetailsUseCase: FetchProfileDetailsUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val args: ProfileScreenArgs = ProfileScreenArgs(savedStateHandle)
    private val _state = MutableStateFlow(ProfileUiState())
    val state = _state.asStateFlow()

    init {
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
        if (_state.value.isMyProfile) {
            getMyProfileDetails()
        } else {
            getUserDetails()
        }
        if (_state.value.isMyProfile.not()) {
            isRequestSent(_state.value.userDetails.userId)
        }
    }

    private fun getMyProfileDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            cachingMyProfileDetails()
            getCachedProfileDetails()
        }
        getFriendsCount()
        getMyPosts()
    }

    private suspend fun cachingMyProfileDetails() {
        try {
            Log.d("rrr", "start caching")
            insertProfileDetailsUseCase()
            Log.d("rrr", "done caching")
            _state.update { it.copy(isLoading = false, isError = false) }
        } catch (e: Exception) {
            Log.d("kkk", "caching network data failed")
            _state.update { it.copy(isLoading = false, isError = true) }
        }

    }

    private suspend fun getCachedProfileDetails() {
        try {
            Log.d("rrr", "get caching")
            val profileUiState = fetchProfileDetailsUseCase().map { it.toUserDetailsUiState() }
            Log.d("rrr", "done getting caching")
            profileUiState.collect { userDetailsUiState ->
                _state.update { profileUiState ->
                    profileUiState.copy(
                        userDetails = profileUiState.userDetails.copy(
                            fullName = userDetailsUiState.fullName,
                            username = userDetailsUiState.username,
                            profileAvatar = userDetailsUiState.profileAvatar,
                            profileCover = userDetailsUiState.profileCover,
                            userId = userDetailsUiState.userId,
                        )
                    )
                }
            }
        } catch (e: Exception) {
            Log.d("kkk", "loading cached data failed")
            _state.update { it.copy(isLoading = false, isError = true) }
        }

    }

    private fun getMyPosts() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                Log.d("rrr", "start get my posts")
                val profilePosts = fetchUserPosts(_state.value.userDetails.userId)
                    .posts.toProfilePostsUiState()
                Log.d("rrr", "done get my posts")
                _state.update {
                    it.copy(
                        isLoading = false,
                        isError = false,
                        profilePosts = profilePosts,
                        userDetails = it.userDetails.copy(
                            postCount = profilePosts.count().toString()
                        )
                    )
                }
            } catch (e: Throwable) {
                Log.d("kkk", "loading my posts failed")
                _state.update { it.copy(isLoading = false, isError = true) }
            }
        }
    }

    private fun getFriendsCount() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                Log.d("rrr", "start get my friends")
                val friendsCount = fetchUserFriendsCount(_state.value.userDetails.userId).total
                Log.d("rrr", "done get my friends")
                _state.update { profileUiState ->
                    profileUiState.copy(
                        userDetails = profileUiState.userDetails.copy(
                            friendsCount = friendsCount.toString()
                        )
                    )
                }
            } catch (e: Throwable) {
                Log.d("kkk", "getting my friends failed")
            }
        }
    }

    private fun getUserDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val userFriendsCount = fetchUserFriendsCount(_state.value.userDetails.userId).total
                val profilePosts =
                    fetchUserPosts(_state.value.userDetails.userId).posts.toProfilePostsUiState()
                val userPostsCount = fetchUserPosts(_state.value.userDetails.userId).count
                val profileUiState =
                    fetchUserDetailS(_state.value.userDetails.userId).toUserDetailsUiState()
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
        if (_state.value.isMyProfile) getMyProfileDetails() else getUserDetails()
        if (_state.value.isMyProfile.not()) {
            isRequestSent(_state.value.userDetails.userId)
        }
    }
}