package com.octopus.socialnetwork.ui.screen.create_post

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octopus.socialnetwork.domain.usecase.post.CreatePostUseCase
import com.octopus.socialnetwork.domain.usecase.post.ImageAnalyzerUserCase
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
    private val createPostUseCase: CreatePostUseCase,
    private val imageAnalyzer: ImageAnalyzerUserCase
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

    fun onClickAddImage() {
        _state.update {
            it.copy(
                isAddNewImage = !state.value.isAddNewImage
            )
        }
    }


    fun onClickChangeImage(file: File) {
        viewModelScope.launch {

            val imageValid = state.value.imageUri?.let { imageAnalyzer(it) }

            setLoading(true)
            if (imageValid == true) {
                val result = createPostUseCase(_state.value.captionText, file)
                result?.let {
                    setLoading(false)
                    uploadPostsSuccess()
                }

            }
        }

    }

    private fun uploadPostsSuccess() {
        _state.update {
            it.copy(
                isSuccess = true
            )
        }
    }

    private fun setLoading(state: Boolean) {
        _state.update {
            it.copy(
                isLoading = state
            )
        }
    }
}