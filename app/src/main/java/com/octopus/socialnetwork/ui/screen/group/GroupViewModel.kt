package com.octopus.socialnetwork.ui.screen.group

import androidx.lifecycle.ViewModel
import com.octopus.socialnetwork.ui.screen.group.uistate.GroupUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class GroupViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(GroupUiState())
    val state = _state.asStateFlow()


}