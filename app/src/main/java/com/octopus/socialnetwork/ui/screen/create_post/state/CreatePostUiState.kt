package com.octopus.socialnetwork.ui.screen.create_post.state

import android.net.Uri

data class CreatePostUiState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isInvalidImage: Boolean = false,
    val imageUri: Uri? = null,
    val captionText: String = "",
    val isAddNewImage: Boolean = true,
)