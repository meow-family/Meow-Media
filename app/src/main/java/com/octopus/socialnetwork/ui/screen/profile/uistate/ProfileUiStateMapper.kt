package com.octopus.socialnetwork.ui.screen.profile.uistate

import com.octopus.socialnetwork.domain.model.post.Post
import com.octopus.socialnetwork.domain.model.user.UserDetails


fun UserDetails.asProfileUiState(friendsCount: Int, postsCount: Int, profilePosts: List<ProfilePostUiState>): ProfileUiState {
    return ProfileUiState(
        fullName = fullName,
        username = username,
        friendsCount = friendsCount.toString(),
        postCount = postsCount.toString(),
        profileAvatar = avatar,
        profileCover = coverUrl,
        profilePosts = profilePosts
    )
}

fun Post.asProfilePostUiState(): ProfilePostUiState {
    return ProfilePostUiState(
        postId = postId,
        postImage = postImage
    )
}

fun List<Post>.asProfilePostsUiState(): List<ProfilePostUiState> = this.map { it.asProfilePostUiState() }