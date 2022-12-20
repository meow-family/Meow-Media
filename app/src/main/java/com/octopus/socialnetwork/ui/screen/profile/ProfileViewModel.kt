package com.octopus.socialnetwork.ui.screen.profile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octopus.socialnetwork.domain.usecase.user.friend_requests.AddFriendUseCase
import com.octopus.socialnetwork.domain.usecase.user.CheckUserFriendUseCase
import com.octopus.socialnetwork.domain.usecase.user.FetchUserDetailsUseCase
import com.octopus.socialnetwork.domain.usecase.user.FetchUserFriendsUseCase
import com.octopus.socialnetwork.domain.usecase.user.FetchUserIdUseCase
import com.octopus.socialnetwork.domain.usecase.user.FetchUserPostsUseCase
import com.octopus.socialnetwork.domain.usecase.user.friend_requests.RemoveFriendUseCase
import com.octopus.socialnetwork.ui.screen.profile.mapper.toProfilePostsUiState
import com.octopus.socialnetwork.ui.screen.profile.mapper.toUserDetailsUiState
import com.octopus.socialnetwork.ui.screen.profile.uistate.ProfileUiState
import dagger.hilt.android.lifecycle.HiltViewModel
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
    private val userIdVisitor: FetchUserIdUseCase,
    private val addFriendUseCase: AddFriendUseCase,
    private val removeFriendUseCase: RemoveFriendUseCase,
    private val checkUserFriendUseCase: CheckUserFriendUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val args: ProfileScreenArgs = ProfileScreenArgs(savedStateHandle)

    private val _state = MutableStateFlow(ProfileUiState())
    val state = _state.asStateFlow()

    init {
        val currentUserId = getCurrentUserId()

        getUserDetails(currentUserId)
        isRequestSent(currentUserId, userIdVisitor())
    }


    private fun getCurrentUserId(): Int {
        val currentUserId = args.userIdVisitor?.toIntOrNull()
        return if (currentUserId != null) {

            val isNotUserVisitor = userIdVisitor() != currentUserId
            _state.update { it.copy(isUserVisitor = isNotUserVisitor) }
            currentUserId

        } else {
            userIdVisitor()
        }
    }

    private fun getUserDetails(currentUserId: Int) {
        try {
            viewModelScope.launch {
                val userFriendsCount = fetchUserFriendsCount(currentUserId).total
                val profilePosts = fetchUserPosts(currentUserId).posts.toProfilePostsUiState()
                val userPostsCount = fetchUserPosts(currentUserId).count
                val profileUiState = fetchUserDetailS(currentUserId)

                _state.update {
                    it.copy(
                        isLoading = false,
                        isError = false,
                        profilePosts = profilePosts,
                        userDetails = it.userDetails.copy(
//                            fullName = profileUiState.collect(),
//                            username = profileUiState.username,
                            friendsCount = userFriendsCount.toString(),
                            postCount = userPostsCount.toString(),
                        )
                    )
                }
            }
        } catch (e: Exception) {
            _state.update { it.copy(isLoading = false, isError = true) }
        }
    }

    private fun isRequestSent(currentUserId: Int, visitedUserId: Int) {
        viewModelScope.launch {
            val isRequestSent = checkUserFriendUseCase(currentUserId, visitedUserId).requestExists
            _state.update {
                it.copy(isRequestExists = isRequestSent)
            }
        }
    }

    fun onClickAddFriend() {
        viewModelScope.launch {
            if (!_state.value.isRequestExists) {
                val result = addFriendUseCase(16)
                _state.update {
                    it.copy(
                        isRequestExists = result.requestExists,
                        isRequestSent = result.success,
                        isFriend = result.isFriend
                    )
                }
            } else {
                removeFriendUseCase(16)
                _state.update {
                    it.copy(
                        isRequestExists = false,
                        isRequestSent = false,
                        isFriend = false,
                    )
                }
            }

        }
    }


    fun onClickMessage(){
        //
    }

    fun onClickLogout() {
        //
    }

    fun onClickEditeProfile() {
        //
    }
}