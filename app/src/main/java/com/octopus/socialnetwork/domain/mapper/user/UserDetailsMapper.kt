package com.octopus.socialnetwork.domain.mapper.user

import com.octopus.socialnetwork.data.remote.response.dto.user.UserDto
import com.octopus.socialnetwork.data.remote.response.dto.user.UserFriendsDto
import com.octopus.socialnetwork.data.remote.response.dto.user.UserPostsDto
import com.octopus.socialnetwork.domain.mapper.posts.asPost
import com.octopus.socialnetwork.domain.model.user.UserDetails
import com.octopus.socialnetwork.domain.model.user.UserFriends
import com.octopus.socialnetwork.domain.model.user.UserPosts

fun UserDto.asUserDetails(): UserDetails {
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


fun UserFriendsDto.asUserFriends(): UserFriends {
    return UserFriends(
        total = total ?: 0,
        friends = friends?.map { it.asUserDetails() } ?: emptyList()
    )
}

fun UserPostsDto.asUserPosts(): UserPosts {
    return UserPosts(
        posts = posts?.map { it.asPost() } ?: emptyList(),
        count = count ?: 0
    )
}