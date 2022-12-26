package com.octopus.socialnetwork.domain.mapper.user

import com.octopus.socialnetwork.data.remote.response.dto.user.UserDto
import com.octopus.socialnetwork.data.remote.response.dto.user.FriendsDto
import com.octopus.socialnetwork.data.remote.response.dto.user.PostsDto
import com.octopus.socialnetwork.domain.mapper.posts.toPost
import com.octopus.socialnetwork.domain.model.user.User
import com.octopus.socialnetwork.domain.model.user.Friends
import com.octopus.socialnetwork.domain.model.user.Posts

fun UserDto.toUser(): User {
    return User(
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


fun FriendsDto.toFriends(): Friends {
    return Friends(
        total = total ?: 0,
        friends = friends?.map { it.toUser() } ?: emptyList()
    )
}

fun PostsDto.toPosts(): Posts {
    return Posts(
        posts = posts?.map { it.toPost() }?: emptyList(),
        count = count ?: 0
    )
}