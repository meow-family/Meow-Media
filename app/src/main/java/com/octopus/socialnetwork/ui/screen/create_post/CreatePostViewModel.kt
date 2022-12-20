package com.octopus.socialnetwork.ui.screen.create_post

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octopus.socialnetwork.domain.usecase.post.CreatePostUseCase
import com.octopus.socialnetwork.ui.screen.create_post.state.CreatePostUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class CreatePostViewModel @Inject constructor(
    private val createPostUseCase: CreatePostUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CreatePostUiState())
    val state = _state.asStateFlow()

    fun setImageUri(imageUri: Uri?) {
        _state.update {
            it.copy(
                imageUri = imageUri
            )
        }
    }

    fun onChangeCaptionText(captionText: String) {
        _state.update {
            it.copy(
                captionText = captionText
            )
        }
    }

    fun onClickChangeImage(file: File) {
        _state.update {
            it.copy(
                isSuccess = true
            )
        }
        viewModelScope.launch {
            Log.d("kkk", file.toString())
            createPostUseCase("meow meoow", file)
        }
    }
}