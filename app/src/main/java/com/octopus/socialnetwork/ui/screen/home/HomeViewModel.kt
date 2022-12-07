package com.octopus.socialnetwork.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octopus.socialnetwork.domain.usecase.like.LikeUseCase
import com.octopus.socialnetwork.domain.usecase.like.UnlikeUseCase
import com.octopus.socialnetwork.domain.usecase.post.FetchNewsFeedPostUseCase
import com.octopus.socialnetwork.ui.screen.home.uistate.HomeUiState
import com.octopus.socialnetwork.ui.screen.post.mapper.toPostUiState
import com.octopus.socialnetwork.ui.screen.post.uistate.PostUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.PrimitiveIterator
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchNewsFeedPost: FetchNewsFeedPostUseCase,
    private val likeUseCase: LikeUseCase,
    private val unlikeUseCase: UnlikeUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HomeUiState())
    val state = _state.asStateFlow()


    init {
        getPosts(16)
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

    fun onClickLike(postId: Int) {
        viewModelScope.launch {
            try {
                _state.update {
                    it.copy(isLoading = true)
                }

                _state.value.posts.find { it.postId == postId }?.let { post ->

                    if (post.isLiked) {
                        likeUseCase(userId = 30, contentId = post.postId, typeContent = "post")
                    } else {
                        unlikeUseCase(userId = 30, contentId = post.postId, typeContent = "post")
                    }

                    _state.update {
                        it.copy(
                            isLoading = false,
                            isError = false
                        )
                    }

                    getPosts(16)

                }


            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        isError = true
                    )
                }
            }
        }
    }

    fun onClickComment() {
        //
    }

    fun onClickShare() {
        //
    }


}