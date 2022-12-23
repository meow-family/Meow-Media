package com.octopus.socialnetwork.domain.mapper.posts

import com.octopus.socialnetwork.data.local.entity.PostEntity
import com.octopus.socialnetwork.data.remote.response.dto.post.PostDto
import com.octopus.socialnetwork.domain.model.post.Post
import com.octopus.socialnetwork.ui.util.extensions.removeHtmlEncoding
import com.octopus.socialnetwork.ui.util.extensions.toFormattedDate

fun PostDto.toPost(): Post {
    return Post(
        postId = details?.postId ?: 0,
        ownerId = details?.ownerId ?: 0,
        description = description?.removeHtmlEncoding() ?: "",
        image = image ?: "",
        fullName = posted_user?.fullName ?: "",
        username = posted_user?.username ?: "",
        avatar = posted_user?.avatar?.larger ?: "",
        totalLikes = details?.totalLikes ?: 0,
        totalComments = details?.totalComments ?: 0,
        isLikedByUser = details?.isLikedByUser ?: false,
        timeCreated = details?.timeCreated.toFormattedDate()
    )
}

fun PostEntity.toPost(): Post {
    return Post(
        postId = id,
        ownerId = ownerId,
        description = description,
        image = imageUrl,
        fullName = fullName,
        username = username,
        avatar = avatarUrl,
        totalLikes = totalLikes,
        totalComments = totalComments,
        isLikedByUser = isLikedByUser,
        timeCreated = timeCreated.toFormattedDate(),
    )
}