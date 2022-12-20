package com.octopus.socialnetwork.ui.screen.create_post.state

import android.net.Uri

data class CreatePostUiState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isValidImage: Boolean = false,
    val displayErrorsWrongImage: Boolean = false,
    val imageUri: Uri? = null,
    val captionText: String = ""

)