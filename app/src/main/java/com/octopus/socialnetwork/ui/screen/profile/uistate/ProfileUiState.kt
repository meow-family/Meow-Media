package com.octopus.socialnetwork.ui.screen.profile.uistate

import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.util.enums.UserRelationUiState

data class ProfileUiState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val isMyProfile: Boolean = false,
    val isUserVisitor: Boolean = false,
    val isLogout: Boolean = false,
    val userDetails: UserDetailsUiState = UserDetailsUiState(),
    val profilePosts: List<ProfilePostUiState> = emptyList(),
    val isFriend: Boolean = false,
    val isRequestExists: Boolean = false,
    val profileContentTab: ProfileContentTab = ProfileContentTab(),
    val friends: List<UserDetailsUiState> = emptyList(),

    )

data class UserDetailsUiState(
    val userId: Int = 0,
    val fullName: String = "",
    val username: String = "",
    val friendsCount: String = "0",
    val postCount: String = "0",
    val profileAvatar: String = "",
    val profileCover: String = "",
    var relation: UserRelationUiState = UserRelationUiState.ME,
)

data class ProfileContentTab(
    val itemTabs: List<Int> = listOf(R.string.resents, R.string.albums)
)
