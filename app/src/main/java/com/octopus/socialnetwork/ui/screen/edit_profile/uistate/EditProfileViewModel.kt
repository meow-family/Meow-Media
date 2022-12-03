package com.octopus.socialnetwork.ui.screen.edit_profile.uistate

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
import com.octopus.socialnetwork.domain.usecase.user.FetchUserEditUseCase
import com.octopus.socialnetwork.ui.screen.profile.uistate.ProfileUiState
import com.octopus.socialnetwork.ui.screen.profile.uistate.asProfileUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class EditProfileViewModel @Inject constructor(

    private val fetchUserEditUseCase: FetchUserEditUseCase,
    private val repository: SocialRepository

) : ViewModel() {

    private val _state = MutableStateFlow(EditProfileUiState())
    val state = _state.asStateFlow()


    private fun updateUiState(editProfileUiState: EditProfileUiState) {
        _state.update {
            it.copy(
                firstName = editProfileUiState.firstName,
                lastName = editProfileUiState.lastName,
                fullname = editProfileUiState.fullname,
                email = editProfileUiState.email,
                profileAvatar = editProfileUiState.profileAvatar,
                profileCover = editProfileUiState.profileCover
            )
        }

        Log.i("PROFILE_INFO", "PROFILE_INFO ${editProfileUiState.firstName}")
    }

}