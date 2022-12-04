package com.octopus.socialnetwork.domain.mapper.user

import com.octopus.socialnetwork.data.remote.response.dto.user.UserDTO
import com.octopus.socialnetwork.data.remote.response.dto.user.UserFriendsDTO
import com.octopus.socialnetwork.data.remote.response.dto.user.UserPostsDTO
import com.octopus.socialnetwork.domain.mapper.posts.asPost
import com.octopus.socialnetwork.domain.model.user.UserDetails
import com.octopus.socialnetwork.domain.model.user.UserFriends
import com.octopus.socialnetwork.domain.model.user.UserPosts

fun UserDTO.asUserDetails(): UserDetails {
    return UserDetails(
        id = id ?: 0,
        firstName = firstName ?: "",
        lastName = lastName ?: "",
        fullName = fullName ?: "",
        username = username ?: "",
        email = email ?: "",
        birthDate = birthDate ?: "",
        gender = gender ?: "",
        avatar = avatar?.larger ?: "",
        coverUrl = coverUrl ?: "",
        language = language ?: "",
    )
}


fun UserFriendsDTO.asUserFriends(): UserFriends {
    return UserFriends(
        total = total ?: 0,
        friends = friends?.map { it.asUserDetails() } ?: emptyList()
    )
}

fun UserPostsDTO.asUserPosts(): UserPosts {
    return UserPosts(
        posts = posts?.map { it.asPost() } ?: emptyList(),
        count = count ?: 0
    )
}