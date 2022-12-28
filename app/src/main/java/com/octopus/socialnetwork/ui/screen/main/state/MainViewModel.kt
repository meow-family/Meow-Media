package com.octopus.socialnetwork.ui.screen.main.state

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octopus.socialnetwork.domain.usecase.authentication.firebase.StoreUserTokenUseCase
import com.octopus.socialnetwork.domain.usecase.authentication.logout.CheckIsLogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val storeUserToken: StoreUserTokenUseCase,
    private val checkIsLogout: CheckIsLogoutUseCase,
) : ViewModel() {

    val appState = MutableStateFlow(MainUiState())

    init {

        viewModelScope.launch(Dispatchers.IO) {
            checkIsLogout().collect { logoutState ->
                Log.i("LOGOUT", "logout state is $logoutState")
                appState.update {
                    it.copy(
                        isLoading = false,
                        isLoggedOut = logoutState
                    )
                }
            }
        }

        viewModelScope.launch(Dispatchers.IO) {
            storeUserToken()
        }
    }

}