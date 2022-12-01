package com.octopus.socialnetwork.ui.screen.profile

import androidx.lifecycle.ViewModel
import com.octopus.socialnetwork.ui.screen.profile.uistate.ProfileUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel  @Inject constructor(

) : ViewModel(){

    private val _state = MutableStateFlow(ProfileUiState())
    val state = _state.asStateFlow()


    fun onClickFollow(){
        //
    }

    fun onClickMessage(){
        //
    }
}