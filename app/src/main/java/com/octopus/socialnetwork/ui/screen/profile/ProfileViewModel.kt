package com.octopus.socialnetwork.ui.screen.profile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octopus.socialnetwork.domain.usecase.user.AddFriendUseCase
import com.octopus.socialnetwork.domain.usecase.user.CheckUserFriendUseCase
import com.octopus.socialnetwork.domain.usecase.user.FetchUserDetailsUseCase
import com.octopus.socialnetwork.domain.usecase.user.FetchUserFriendsUseCase
import com.octopus.socialnetwork.domain.usecase.user.FetchUserIdUseCase
import com.octopus.socialnetwork.domain.usecase.user.FetchUserPostsUseCase
import com.octopus.socialnetwork.domain.usecase.user.RemoveFriendUseCase
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
    private val fetchUserIdUseCase: FetchUserIdUseCase,
    private val addFriendUseCase: AddFriendUseCase,
    private val removeFriendUseCase: RemoveFriendUseCase,
    private val checkUserFriendUseCase: CheckUserFriendUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    
    //private val args: ProfileScreenArgs = ProfileScreenArgs(savedStateHandle)

    private val _state = MutableStateFlow(ProfileUiState())
    val state = _state.asStateFlow()

    init {
//        checkUserVisitor()
//        getUserDetails(fetchUserIdUseCase(), args.userIdVisitor)
//        isRequestSent(fetchUserIdUseCase(), args.userIdVisitor)
//
      //  checkUserVisitor()
        getUserDetails(fetchUserIdUseCase(), fetchUserIdUseCase())
        isRequestSent(fetchUserIdUseCase(), fetchUserIdUseCase())
    }
//
//    private fun checkUserVisitor() {
//        val isUserVisitor = fetchUserIdUseCase() != args.userIdVisitor
//
//        _state.update { it.copy(isUserVisitor = isUserVisitor) }
//
//    }

    private fun getUserDetails(currentUserId: Int, visitedUserId: Int) {
        try {
            viewModelScope.launch {
                val userFriendsCount = fetchUserFriendsCount(currentUserId).total
                val profilePosts = fetchUserPosts(visitedUserId).posts.toProfilePostsUiState()
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
            val isRequestSent =
                checkUserFriendUseCase(currentUserId, visitedUserId).requestExists
            _state.update {
                it.copy(
                    isRequestExists = isRequestSent
                )
            }
        }
    }

    fun onClickAddFriend() {
        viewModelScope.launch {
            if (!_state.value.isRequestExists) {
                val result = addFriendUseCase(30, 20)
                _state.update {
                    it.copy(
                        isRequestExists = result.requestExists,
                        isRequestSent = result.success,
                        isFriend = result.isFriend
                    )
                }
            } else {
                removeFriendUseCase(30, 20)
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

    fun onChangeTabIndex(index: Int) {
        _state.update {
            it.copy(
                profileContentTab = it.profileContentTab.copy(
                    selectedTabIndex = index
                )
            )
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