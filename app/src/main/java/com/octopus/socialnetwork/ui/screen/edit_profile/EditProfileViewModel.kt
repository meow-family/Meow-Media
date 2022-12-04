package com.octopus.socialnetwork.ui.screen.edit_profile


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octopus.socialnetwork.domain.usecase.user.UpdateUserInfoUseCase
import com.octopus.socialnetwork.ui.screen.edit_profile.uistate.EditProfileUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val updateUserInfoUseCase: UpdateUserInfoUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(EditProfileUiState())
    val state = _state.asStateFlow()

    init {
        updateUserData()
    }


    private fun updateUserData() {
        viewModelScope.launch {
            try {
//                updateUserInfoUseCase(
//                   currentUserId = 1,
//                   firstName = _state.value.firstName,
//                   lastName = _state.value.lastName,
//                   email = _state.value.email,
//                   currentPassword = _state.value.currentPassword,
//                   newPassword = _state.value.newPassword
//                )
              val result =  updateUserInfoUseCase(
                    currentUserId = 16,
                    firstName = "Asoo",
                    lastName ="Chan",
                    email ="sehunexo710@gmail.com",
                    currentPassword = "asiasama12345",
                    newPassword = "asooosama12345"
                )
                Log.i("MALT","UPDATE my data successfully $result")

            } catch (e: Exception) {

            }
        }


    }

}