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
    private val  fetchUserPostsCount: FetchUserPostsCountUseCase,
    private val  fetchUserPosts: FetchUserPostsUseCase,
    private val repository: SocialRepository

    ) : ViewModel(){

    private val _state = MutableStateFlow(ProfileUiState())
    val state = _state.asStateFlow()

    init {
        getUserDetails(20, 20)
    }

    private fun getUserDetails(currentUserId: Int, visitedUserId: Int){
        try {
            viewModelScope.launch {
                val userFriendsCount = fetchUserFriends(currentUserId).total
                val userPostsCount = fetchUserPostsCount(currentUserId, visitedUserId)
                val profilePosts = fetchUserPosts(currentUserId, visitedUserId).posts.asProfilePostsUiState()
                val profileUiState = fetchUserDetailS(currentUserId).asProfileUiState()

                _state.update {
                    it.copy(
                        fullName = profileUiState.fullName,
                        username = profileUiState.username,
                        friendsCount = userFriendsCount.toString(),
                        postCount = userPostsCount.toString(),
                        profileAvatar = profileUiState.profileAvatar,
                        profileCover = profileUiState.profileCover,
                        profilePosts = profilePosts
                    )
                }

                Log.i("PROFILE_INFO","PROFILE_INFO ${profileUiState.username}")
                Log.i("PROFILE_INFO","PROFILE_INFO ${profilePosts[0].postId}")

                val result = repository.deleteComment(236,30)
                Log.i("TESTING", "result: $result")
            }
        } catch (e: Exception){

        }
    }


    fun onClickFollow(){
        //
    }

    fun onClickMessage(){
        //
    }
}