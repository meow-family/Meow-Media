package com.octopus.socialnetwork.ui.screen.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.usecase.user.FetchUserDetailsUseCase
import com.octopus.socialnetwork.domain.usecase.user.FetchUserFriendsUseCase
import com.octopus.socialnetwork.domain.usecase.user.FetchUserPostsCountUseCase
import com.octopus.socialnetwork.domain.usecase.notifications.FetchNotificationItemsUseCase
import com.octopus.socialnetwork.domain.usecase.notifications.FetchUserNotificationsCountUseCase
import com.octopus.socialnetwork.domain.usecase.notifications.FetchUserNotificationsUseCase
import com.octopus.socialnetwork.ui.screen.profile.uistate.ProfileUiState
import com.octopus.socialnetwork.ui.screen.profile.uistate.asProfileUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel  @Inject constructor(
    private val  fetchUserDetailsUseCase: FetchUserDetailsUseCase,
    private val  fetchUserFriendsUseCase: FetchUserFriendsUseCase,
    private val  fetchUserDetailsCountUseCase: FetchUserPostsCountUseCase,
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
                val userFriendsCount = fetchUserFriendsUseCase(currentUserId).total
                val userPostsCount = fetchUserDetailsCountUseCase(currentUserId, visitedUserId)
                val profileUiState = fetchUserDetailsUseCase(currentUserId).asProfileUiState(userFriendsCount, userPostsCount)
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
                profileCover = profileUiState.profileCover
            )
        }

        Log.i("PROFILE_INFO","PROFILE_INFO ${profileUiState.username}")
    }



    fun onClickFollow(){
        //
    }

    fun onClickMessage(){
        //
    }
}