package com.octopus.socialnetwork.ui.screen.edit_profile.mapper

import com.octopus.socialnetwork.domain.model.user.User
import com.octopus.socialnetwork.ui.screen.edit_profile.state.EditProfileUiState

fun User.toEditUserUiState(): EditProfileUiState {
    return EditProfileUiState(
        firstName = firstName,
        lastName = lastName,
        email = email,
        profileAvatar = avatar,
        profileCover = coverUrl
    )
}