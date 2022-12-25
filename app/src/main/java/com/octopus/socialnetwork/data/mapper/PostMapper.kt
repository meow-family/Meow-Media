package com.octopus.socialnetwork.data.mapper

import com.octopus.socialnetwork.data.local.entity.PostEntity
import com.octopus.socialnetwork.data.remote.response.dto.post.PostDto

fun PostDto.toPostEntity(): PostEntity {
    return PostEntity(
        id = details?.postId ?: 0,
        ownerId = details?.ownerId ?: 0,
        description = description ?: "",
        imageUrl = image ?: "",
        fullName = user?.fullName ?: "",
        username = user?.username ?: "",
        avatarUrl = user?.avatar?.larger ?: "",
        totalLikes = details?.totalLikes ?: 0,
        totalComments = details?.totalComments ?: 0,
        isLikedByUser = details?.isLikedByUser ?: false,
        timeCreated = details?.timeCreated ?: 0L
    )
}