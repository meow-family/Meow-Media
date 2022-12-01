package com.octopus.socialnetwork.domain.mapper.user_details

import com.octopus.socialnetwork.data.remote.response.dto.user.UserDetailsDTO
import com.octopus.socialnetwork.data.remote.response.dto.user.UserFriendsDTO
import com.octopus.socialnetwork.data.remote.response.dto.user.UserPostsDTO
import com.octopus.socialnetwork.domain.mapper.posts.asPost
import com.octopus.socialnetwork.domain.model.user_details.UserDetails
import com.octopus.socialnetwork.domain.model.user_details.UserFriends
import com.octopus.socialnetwork.domain.model.user_details.UserPosts

fun UserDetailsDTO.asUserDetails(): UserDetails {
    return UserDetails(
        guid = guid ?: 0,
        firstName = firstName ?: "",
        lastName = lastName ?: "",
        fullName =fullName ?: "",
        username = username ?: "",
        email = email ?: "",
        birthDate = birthDate ?: "",
        gender = gender ?: "",
        icon = icon?.larger ?: "",
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