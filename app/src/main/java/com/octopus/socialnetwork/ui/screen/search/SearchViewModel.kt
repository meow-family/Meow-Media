package com.octopus.socialnetwork.ui.screen.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octopus.socialnetwork.domain.usecase.search.SearchUseCase
import com.octopus.socialnetwork.ui.screen.notifications.state.NotificationsUiState
import com.octopus.socialnetwork.ui.screen.search.state.SearchUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _state = MutableStateFlow(SearchUiState())
    val state = _state.asStateFlow()

    init {
        getNotifications()
    }

    private fun getNotifications() {
//        viewModelScope.launch {
//            try {
//                val searchItem =
//                    searchUseCase.invoke() { it.toNotificationsUiState() }
//
//                _state.update {
//                    it.copy(
//                        items = searchItem,
//                        isLoading = false,
//                        isError = false,
//                    )
//                }
//
//            } catch (e: Exception) {
//                _state.update {
//                    it.copy(
//                        isLoading = false,
//                        isError = true
//                    )
//                }
//            }
//        }

    }


}