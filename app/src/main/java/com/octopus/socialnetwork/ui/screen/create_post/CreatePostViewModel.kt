package com.octopus.socialnetwork.ui.screen.create_post

import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octopus.socialnetwork.domain.usecase.post.create_post.CreatePostUseCase
import com.octopus.socialnetwork.domain.usecase.post.create_post.ml_kit.DetectCatUseCase
import com.octopus.socialnetwork.domain.usecase.post.create_post.ml_kit.OpenFileUseCase
import com.octopus.socialnetwork.ui.screen.create_post.state.CreatePostUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreatePostViewModel @Inject constructor(
    private val createPost: CreatePostUseCase,
    private val detectCat: DetectCatUseCase,
    private val openFile: OpenFileUseCase
) : ViewModel() {


    private val _state = MutableStateFlow(CreatePostUiState())
    val state = _state.asStateFlow()

    @RequiresApi(Build.VERSION_CODES.O)
    fun onClickChangeImage(uri: Uri) {
        viewModelScope.launch(Dispatchers.IO) {
            val isImageValid = state.value.imageUri?.let { uri -> detectCat(uri) } ?: false

            setLoading(true)
            if (isImageValid) {
                val result = createPost(_state.value.captionText, openFile(uri))
                result?.let {
                    setLoading(false)
                    onUploadPostSuccess() } ?: setLoading(false)
            } else {
                setLoading(false)
                onInvalidImageDetection()
            }
        }
    }

    private fun setLoading(state: Boolean) {
        _state.update { it.copy(isLoading = state) }
    }

    fun onChangeCaptionText(captionText: String) {
        _state.update { it.copy(captionText = captionText) }
    }

    fun setImageUri(imageUri: Uri?) {
        _state.update { it.copy(imageUri = imageUri) }
    }

    fun onClickAddImage() {
        _state.update { it.copy(isAddNewImage = !state.value.isAddNewImage) }
    }

    private fun onUploadPostSuccess() {
        _state.update { it.copy(isSuccess = true) }
    }

    fun onInvalidImageDetection() {
        _state.update { it.copy(isInvalidImage = !state.value.isInvalidImage) }
    }


}