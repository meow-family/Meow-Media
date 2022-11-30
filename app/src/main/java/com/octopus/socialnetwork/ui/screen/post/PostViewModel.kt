package com.octopus.socialnetwork.ui.screen.post

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class PostViewModel @Inject constructor(

) : ViewModel(){

    private val _state = MutableStateFlow(PostUiState())
    val state = _state.asStateFlow()

    fun like(){
        //
    }
    fun comment(){
        //
    }
    fun share(){
        //
    }

}