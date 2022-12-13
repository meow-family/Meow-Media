package com.octopus.socialnetwork.ui.screen.edit_profile


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octopus.socialnetwork.data.local.datastore.DataStorePreferences
import com.octopus.socialnetwork.domain.usecase.user.ChangeProfileImageUseCase
import com.octopus.socialnetwork.domain.usecase.user.FetchUserDetailsUseCase
import com.octopus.socialnetwork.domain.usecase.user.UpdateUserInfoUseCase
import com.octopus.socialnetwork.ui.screen.edit_profile.mapper.toEditProfileUiState
import com.octopus.socialnetwork.ui.screen.edit_profile.uistate.EditProfileUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val updateUserInfoUseCase: UpdateUserInfoUseCase,
    private val  fetchUserDetails: FetchUserDetailsUseCase,
    private val changeProfileImageUseCase: ChangeProfileImageUseCase,
    dataStorePreferences: DataStorePreferences,

    ) : ViewModel() {

    private val _state = MutableStateFlow(EditProfileUiState())
    val state = _state.asStateFlow()

    init {
       val userId =  19 //dataStorePreferences.readString(SocialNetworkApplication.USER_ID_KEY)
        if(userId != null){
            _state.update { it.copy(userId = userId) }
        }

        showUserData(_state.value.userId)
    }

    fun showUserData(currentUserId: Int) {
        try {
            viewModelScope.launch {
                val profileUiState = fetchUserDetails(currentUserId).toEditProfileUiState()
                _state.update {
                    it.copy(
                        firstName = profileUiState.firstName,
                        lastName = profileUiState.lastName,
                        email = profileUiState.email,
                        profileAvatar = profileUiState.profileAvatar,
                        profileCover = profileUiState.profileCover,
                        isLoading = false,
                        isError = false,
                    )
                }
            }
        } catch (e: Exception) {
            _state.update { it.copy(
                isLoading = false,
                isError = true
            ) }
        }
    }
    
    private fun updateUserData(currentUserId: Int) {
        viewModelScope.launch {
            try {
                updateUserInfoUseCase(
                    currentUserId = currentUserId,
                    firstName = _state.value.firstName,
                    lastName = _state.value.lastName,
                    email = _state.value.email,
                    currentPassword = _state.value.currentPassword,
                    newPassword = _state.value.newPassword,
                    newGender = _state.value.gender,
                )
                _state.update {
                    it.copy(
                        isLoading = false,
                        isSuccess = true,
                        isError = false,
                    )
                }
                Log.d("wsh", "in viewModel-- image === ${_state.value.profileAvatar}")

                changeProfileImageUseCase(
//                    file = _state.value.profileAvatarToEdit,
                    file = _state.value.profileAvatar,
                    userId = currentUserId,
                )
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        isSuccess = false,
                        isError = true,
                    )
                }
            }
        }
    }
    
    fun onClickSave() {
        updateUserData(_state.value.userId)
    }

    fun onChangeImage(image: String){
//        val file = File(image)
        _state.update {
            it.copy(
//                profileAvatarToEdit = image
                profileAvatar = image
            )
        }
    }
    fun onChangeFirstName(newValue: String) {
        _state.update { it.copy(firstName = newValue) }
    }

    fun onChangeLastName(newValue: String) {
        _state.update { it.copy(lastName = newValue) }
    }

    fun onChangeEmail(newValue: String) {
        _state.update { it.copy(email = newValue) }
    }

    fun onChangeCurrentPassword(newValue: String) {
        _state.update { it.copy(currentPassword = newValue) }
    }

    fun onChangeNewPassword(newValue: String) {
        _state.update { it.copy(newPassword = newValue) }
    }


}