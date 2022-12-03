package com.octopus.socialnetwork.ui.screen.home

import androidx.lifecycle.ViewModel
import com.octopus.socialnetwork.ui.screen.home.uistate.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

) : ViewModel() {

    private val _state = MutableStateFlow(HomeUiState())
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