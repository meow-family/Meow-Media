package com.octopus.socialnetwork.ui.screen.post

import androidx.lifecycle.ViewModel
import com.octopus.socialnetwork.ui.screen.post.uistate.PostUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class PostViewModel @Inject constructor(

) : ViewModel(){

    private val _state = MutableStateFlow(PostUiState())
    val state = _state.asStateFlow()

    fun onClickLike(){
        //
    }
    fun onClickComment(){
        //
    }
    fun onClickShare(){
        //
    }

}