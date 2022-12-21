package com.octopus.socialnetwork.ui.screen.edit_profile.uistate

data class EditProfileUiState(
    val firstName: String = "",
    val lastName: String = "",
    val currentPassword: String = "",
    val newPassword: String = "",
    val email: String = "",
    val profileAvatar: String = "",
    val profileCover: String = "",
    val isLoading: Boolean = true,
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    var showCurrentPassword: Boolean = false,
    var showNewPassword: Boolean = false,
)