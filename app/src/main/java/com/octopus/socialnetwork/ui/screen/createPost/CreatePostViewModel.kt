package com.octopus.socialnetwork.ui.screen.createPost

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octopus.socialnetwork.domain.usecase.post.CreatePostUseCase
import com.octopus.socialnetwork.domain.usecase.user.FetchUserIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class CreatePostViewModel @Inject constructor(
    private val createPostUseCase: CreatePostUseCase
) : ViewModel(){

    fun onClickChangeImage(file: File) {
        viewModelScope.launch {
            Log.d("kkk",file.toString())
            createPostUseCase("meow meoow",file)
        }
    }
}