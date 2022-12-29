package com.octopus.socialnetwork.ui.screen.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octopus.socialnetwork.domain.usecase.authentication.firebase.StoreUserTokenUseCase
import com.octopus.socialnetwork.domain.usecase.authentication.logout.CheckIsLoggedInUseCase
import com.octopus.socialnetwork.ui.screen.main.state.MainUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val storeUserToken: StoreUserTokenUseCase,
    private val checkIsLoggedIn: CheckIsLoggedInUseCase,
) : ViewModel() {

    val appState = MutableStateFlow(MainUiState())

    init {

        viewModelScope.launch(Dispatchers.IO) {
            checkIsLoggedIn().collect { loginState ->
                Log.i("LOGOUT", "logout state is ${loginState}")
                appState.update {
                    it.copy(
                        isLoading = false,
                        isLoggedIn = loginState
                    )
                }
            }
        }

        viewModelScope.launch(Dispatchers.IO) {
            storeUserToken()
        }
    }

}