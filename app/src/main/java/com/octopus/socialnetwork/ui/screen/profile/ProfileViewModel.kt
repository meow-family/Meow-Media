package com.octopus.socialnetwork.ui.screen.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.usecase.user.FetchUserDetailsUseCase
import com.octopus.socialnetwork.domain.usecase.user.FetchUserFriendsUseCase
import com.octopus.socialnetwork.domain.usecase.user.FetchUserPostsCountUseCase
import com.octopus.socialnetwork.domain.usecase.user.FetchUserPostsUseCase
import com.octopus.socialnetwork.ui.screen.profile.uistate.ProfileUiState
import com.octopus.socialnetwork.ui.screen.profile.uistate.asProfilePostsUiState
import com.octopus.socialnetwork.ui.screen.profile.uistate.asProfileUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel  @Inject constructor(
    private val  fetchUserDetailS: FetchUserDetailsUseCase,
    private val  fetchUserFriends: FetchUserFriendsUseCase,
    private val  fetchUserDetailsCount: FetchUserPostsCountUseCase,
    private val  fetchUserPostsUseCase: FetchUserPostsUseCase,
    private val repository: SocialRepository

    ) : ViewModel(){

    private val _state = MutableStateFlow(ProfileUiState())
    val state = _state.asStateFlow()

    init {
        getUserDetails(16, 28)
    }

    private fun getUserDetails(currentUserId: Int, visitedUserId: Int){
        try {
            viewModelScope.launch {
                val userFriendsCount = fetchUserFriends(currentUserId).total
                val userPostsCount = fetchUserDetailsCount(currentUserId, visitedUserId)
                val profilePosts = fetchUserPostsUseCase(currentUserId, visitedUserId).posts.asProfilePostsUiState()
                val profileUiState = fetchUserDetailS(currentUserId).asProfileUiState(userFriendsCount, userPostsCount, profilePosts)

                updateUiState(profileUiState)

                val result = repository.deleteComment(236,30)
                Log.i("TESTING", "result: $result")
            }
        } catch (e: Exception){

        }
    }

    private fun updateUiState(profileUiState: ProfileUiState){
        _state.update {
            it.copy(
                fullName = profileUiState.fullName,
                username = profileUiState.username,
                friendsCount = profileUiState.friendsCount,
                postCount = profileUiState.postCount,
                profileAvatar = profileUiState.profileAvatar,
                profileCover = profileUiState.profileCover,
                profilePosts = profileUiState.profilePosts
            )
        }

        Log.i("PROFILE_INFO","PROFILE_INFO ${profileUiState.username}")
        Log.i("PROFILE_INFO","PROFILE_INFO ${profileUiState.profilePosts[0].postId}")
    }



    fun onClickFollow(){
        //
    }

    fun onClickMessage(){
        //
    }
}