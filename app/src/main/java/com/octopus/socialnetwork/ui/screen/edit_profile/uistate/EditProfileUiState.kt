package com.octopus.socialnetwork.ui.screen.edit_profile.uistate

data class EditProfileUiState(
    val firstName: String = "",
    val lastName: String = "",
    val currentPassword: String = "",
    val newPassword: String = "",
    val email: String = "0",
    val profileAvatar: String = "",
    val profileCover: String = ""
)