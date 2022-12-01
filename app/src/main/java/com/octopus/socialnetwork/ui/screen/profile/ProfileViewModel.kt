package com.octopus.socialnetwork.ui.screen.profile

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel  @Inject constructor(
    private val  fetchUserDetailsUseCase: FetchUserDetailsUseCase,
    private val  fetchUserFriendsUseCase: FetchUserFriendsUseCase,
    private val  fetchUserPostsUseCase : FetchUserPostsUseCase,
) : ViewModel(){

    private val _state = MutableStateFlow(ProfileUiState())
    val state = _state.asStateFlow()

    init {
        getUserDetails(16, 28)
    }

    private fun getUserDetails(userId: Int, userFriendId: Int){
        try {
            viewModelScope.launch {
                val userFriendsCount = fetchUserFriendsUseCase(userId).total
                val userPostsCount = fetchUserPostsUseCase(userId, userFriendId).count
                val profileUiState = fetchUserDetailsUseCase(userId).asProfileUiState(userFriendsCount, userPostsCount)
                onSuccess(profileUiState)
            }
        } catch (e: Exception){

        }
    }

    private fun onSuccess(profileUiState: ProfileUiState){
        _state.update {
            it.copy(
                fullName = profileUiState.fullName,
                username = profileUiState.username,
                friendsCount = profileUiState.friendsCount,
                postCount = profileUiState.postCount,
                profileAvatar = profileUiState.profileAvatar,
                profileCover = profileUiState.profileCover
            )
        }

        Log.i("PROFILE_INFO","PROFILE_INFO ${profileUiState.username}")
    }

    private fun UserDetails.asProfileUiState(friendsCount: Int, postsCount: Int): ProfileUiState {
        return ProfileUiState(
            fullName = fullName,
            username = username,
            friendsCount = friendsCount.toString(),
            postCount = postsCount.toString(),
            profileAvatar = icon,
            profileCover = coverUrl
        )
    }

    fun onClickFollow(){
        //
    }

    fun onClickMessage(){
        //
    }
}