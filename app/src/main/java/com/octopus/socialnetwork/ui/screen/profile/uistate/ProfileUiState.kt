package com.octopus.socialnetwork.ui.screen.profile.uistate

import com.octopus.socialnetwork.R

data class ProfileUiState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val isMyProfile: Boolean = false,
    val userDetails: UserDetailsUiState = UserDetailsUiState(),
    val profilePosts: List<ProfilePostUiState> = emptyList(),
    val isFriend: Boolean = false,
    val isRequestExists: Boolean = false,
    val profileContentTab: ProfileContentTab = ProfileContentTab()

)

data class UserDetailsUiState(
    val userId : Int = 0,
    val fullName: String = "",
    val username: String = "",
    val friendsCount: String = "0",
    val postCount: String = "0",
    val profileAvatar: String = "",
    val profileCover: String = "",
)

data class ProfileContentTab(
    val itemTabs: List<Int> = listOf(R.string.resents, R.string.albums)
)
