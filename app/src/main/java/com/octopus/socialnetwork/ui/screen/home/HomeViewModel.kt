package com.octopus.socialnetwork.ui.screen.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octopus.socialnetwork.domain.usecase.album.FetchAlbumsUserUseCase
import com.octopus.socialnetwork.domain.usecase.post.FetchNewsFeedPostUseCase
import com.octopus.socialnetwork.ui.screen.home.uistate.AlbumUiState
import com.octopus.socialnetwork.ui.screen.home.uistate.HomeUiState
import com.octopus.socialnetwork.ui.screen.post.mapper.toPostUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchNewsFeedPost: FetchNewsFeedPostUseCase,
    private val fetchAlbumsUserUseCase: FetchAlbumsUserUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HomeUiState())
    private val _stateAlbum = MutableStateFlow(AlbumUiState())
    val state = _state.asStateFlow()
    val stateAlbum = _stateAlbum.asStateFlow()


    init {
        getPosts(16)
        getAlbums(16, 20)
    }

    private fun getPosts(currentUserId: Int) {
        viewModelScope.launch {
            try {
                val post = fetchNewsFeedPost(currentUserId).map { it.toPostUiState() }
                _state.update {
                    it.copy(
                        posts = post,
                        isLoading = false,
                        isSuccess = true,
                        isError = false
                    )
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        isSuccess = false,
                        isError = true
                    )
                }
            }
        }
    }


    private fun getAlbums(ownerAlbumsUserId: Int, visitedUserId: Int) {
        viewModelScope.launch {
            try {
                val albums = fetchAlbumsUserUseCase(
                    ownerAlbumsUserId = ownerAlbumsUserId,
                    visitedUserId = visitedUserId
                )
                _stateAlbum.update {
                    it.copy(
                        albums = albums.,
                        isLoading = false,
                        isSuccess = true,
                        isError = false
                    )
                }
                Log.i("TESTING", "result: $albums")
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        isSuccess = false,
                        isError = true
                    )
                }
            }
        }
    }


    fun onClickLike() {
        //
    }

    fun onClickComment() {
        //
    }

    fun onClickShare() {
        //
    }


}