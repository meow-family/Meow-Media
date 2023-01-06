package com.octopus.socialnetwork.ui.screen.profile.state

import androidx.paging.PagingData
import com.octopus.socialnetwork.ui.util.UserRelationUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class ProfileUiState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val isMyProfile: Boolean = false,
    val isUserVisitor: Boolean = false,
    val userDetails: UserDetailsUiState = UserDetailsUiState(),
    val profilePosts: Flow<PagingData<ProfilePostUiState>> = emptyFlow(),
    val isFriend: Boolean = false,
    val isRequestExists: Boolean = false,
    val friends: List<UserDetailsUiState> = emptyList(),
)

data class UserDetailsUiState(
    val userId: Int = 0,
    val fullName: String = "",
    val username: String = "",
    val friendsCount: String = "",
    val postCount: String = "",
    val profileAvatar: String = "",
    val profileCover: String = "",
    var relation: UserRelationUiState = UserRelationUiState.ME,
)

data class ProfilePostUiState(
    val postId: Int = 0,
    val postOwnerId: Int = 0,
    val postImage: String = ""
)