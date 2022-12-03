package com.octopus.socialnetwork.domain.mapper.posts

import com.octopus.socialnetwork.data.remote.response.dto.post.PostDTO
import com.octopus.socialnetwork.data.remote.response.dto.post.PostDetailsDTO
import com.octopus.socialnetwork.domain.model.post.Post

fun PostDTO.asPost(): Post {
    return Post(
        postId = details.postId ?: 0,
        ownerId = details.ownerId ?: 0,
        description = description,
        image = image,
        fullName = posted_user.fullName ?: "",
        username = posted_user.username ?: "",
        avatar = posted_user.avatar?.larger ?: "",
        totalLikes = details.totalLikes ?: 0,
        totalComments = details.totalComments ?: 0,
        isLikedByUser = details.isLikedByUser ?: false
    )
}

fun PostDetailsDTO.asPost(): Post {
    return Post(
        postId = postId ?: 0,
        ownerId = ownerId ?: 0,
        description = title ?: "",
        image = image ?: "",
        fullName = "",
        username = "",
        avatar = "",
        totalLikes = totalLikes ?: 0,
        totalComments = totalComments ?: 0,
        isLikedByUser = isLikedByUser ?: false
    )
}