package com.octopus.socialnetwork.ui.screen.edit_profile


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octopus.socialnetwork.domain.usecase.user.UpdateUserInfoUseCase
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
) : ViewModel() {

    private val _state = MutableStateFlow(EditProfileUiState())
    val state = _state.asStateFlow()

    private fun updateUserData() {
        viewModelScope.launch {
            try {
                updateUserInfoUseCase(
                    currentUserId = 16,
                    firstName = _state.value.firstName,
                    lastName = _state.value.lastName,
                    email = _state.value.email,
                    currentPassword = _state.value.currentPassword,
                    newPassword = _state.value.newPassword,
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

    fun onClickSave() {
        updateUserData()
    }

    fun onChangeImage(image: String){
        _state.update {
            it.copy(
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