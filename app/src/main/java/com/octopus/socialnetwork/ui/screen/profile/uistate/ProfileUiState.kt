package com.octopus.socialnetwork.ui.screen.profile.uistate

data class ProfileUiState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val fullName: String = "",
    val username: String = "",
    val friendsCount :String = "0" ,
    val postCount: String = "0",
    val profileAvatar: String = "",
    val profileCover: String = "",
    val profilePosts: List<ProfilePostUiState> = emptyList(),
    val isFriend: Boolean = false,
    val isRequestExists: Boolean = false,
    val isRequestSent: Boolean = false,
)


