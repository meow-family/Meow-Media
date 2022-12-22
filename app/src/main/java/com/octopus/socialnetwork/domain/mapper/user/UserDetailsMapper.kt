package com.octopus.socialnetwork.domain.mapper.user

import com.octopus.socialnetwork.constants.Constants.INVALID_USER_ID
import com.octopus.socialnetwork.data.remote.response.dto.user.UserDto
import com.octopus.socialnetwork.data.remote.response.dto.user.UserFriendsDto
import com.octopus.socialnetwork.data.remote.response.dto.user.UserPostsDto
import com.octopus.socialnetwork.domain.mapper.posts.toPost
import com.octopus.socialnetwork.domain.model.user.UserDetails
import com.octopus.socialnetwork.domain.model.user.UserFriends
import com.octopus.socialnetwork.domain.model.user.UserPosts

fun UserDto.toUserDetails(): UserDetails {
    return UserDetails(
        id = id ?: INVALID_USER_ID,
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


fun UserFriendsDto.toUserFriends(): UserFriends {
    return UserFriends(
        total = total ?: 0,
        friends = friends?.map { it.toUserDetails() } ?: emptyList()
    )
}

fun UserPostsDto.toUserPosts(): UserPosts {
    return UserPosts(
        posts = posts?.map { it.toPost() }?: emptyList(),
        count = count ?: 0
    )
}