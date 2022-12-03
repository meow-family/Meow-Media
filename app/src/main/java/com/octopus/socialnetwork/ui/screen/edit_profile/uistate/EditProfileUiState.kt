package com.octopus.socialnetwork.ui.screen.edit_profile.uistate

data class EditProfileUiState(
    val firstName: String = "",
    val lastName: String = "",
    val fullname: String = "",
    val email: String = "0",
    val profileAvatar: String = "",
    val profileCover: String = ""
)