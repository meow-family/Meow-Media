package com.octopus.socialnetwork.ui.screen.edit_profile

import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octopus.socialnetwork.domain.usecase.post.OpenFileUseCase
import com.octopus.socialnetwork.domain.usecase.user.ChangeProfileImageUseCase
import com.octopus.socialnetwork.domain.usecase.user.FetchUserDetailsUseCase
import com.octopus.socialnetwork.domain.usecase.user.UpdateUserInfoUseCase
import com.octopus.socialnetwork.ui.screen.edit_profile.mapper.toEditUserUiState
import com.octopus.socialnetwork.ui.screen.edit_profile.uistate.EditProfileUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val updateUserInfoUseCase: UpdateUserInfoUseCase,
    private val fetchUserDetailsUseCase: FetchUserDetailsUseCase,
    private val changeProfileImageUseCase: ChangeProfileImageUseCase,
    private val openFileUseCase: OpenFileUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val args: EditProfileScreenArgs = EditProfileScreenArgs(savedStateHandle)

    private val _state = MutableStateFlow(EditProfileUiState())
    val state = _state.asStateFlow()

    init {
        getUserDetails(args.userId?.toIntOrNull() ?: -1)
    }

    private fun getUserDetails(currentUserId: Int) {
        viewModelScope.launch {
            try {
                val userDetails = fetchUserDetailsUseCase(currentUserId).toEditUserUiState()
                _state.update {
                    it.copy(
                        isLoading = false,
                        isSuccess = true,
                        firstName = userDetails.firstName,
                        lastName = userDetails.lastName,
                        email = userDetails.email,
                        profileAvatar = userDetails.profileAvatar,
                        profileCover = userDetails.profileCover
                    )
                }
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

    private fun updateUserData() {
        viewModelScope.launch {
            try {
                updateUserInfoUseCase(
                    currentUserId = checkNotNull(args.userId?.toInt()),
                    firstName = _state.value.firstName,
                    lastName = _state.value.lastName,
                    email = _state.value.email,
                    currentPassword = _state.value.currentPassword,
                    newPassword = _state.value.newPassword
                )
                _state.update {
                    it.copy(
                        isLoading = false,
                        isSuccess = true,
                        isError = false,
                    )
                }

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

    @RequiresApi(Build.VERSION_CODES.O)
    fun onClickChangeImage(uri: Uri) {
        viewModelScope.launch {
            try {
                Log.d("kkk",uri.toString())
                val updatedUser = changeProfileImageUseCase(profileImage = openFileUseCase(uri))
                Log.d("kkk",updatedUser.avatar)
                _state.update { it.copy(profileAvatar = updatedUser.avatar) }
            } catch (e: Throwable) {
                Log.d("kkk","error")
                _state.update { it.copy(isLoading = false, isError = true) }
            }
        }
    }

    fun onClickSave() {
        updateUserData()
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

    fun onChangeCurrentPasswordVisibility() {
        _state.update { it.copy(showCurrentPassword = !it.showCurrentPassword) }
    }

    fun onChangeNewPasswordVisibility() {
        _state.update { it.copy(showNewPassword = !it.showNewPassword) }
    }
}