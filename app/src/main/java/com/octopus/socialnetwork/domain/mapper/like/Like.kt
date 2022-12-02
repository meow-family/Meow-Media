package com.octopus.socialnetwork.domain.mapper.like

import com.octopus.socialnetwork.data.remote.response.dto.like.LikeDTO
import com.octopus.socialnetwork.domain.model.like.Like

fun LikeDTO.asLike(): Like {
    return Like(
        count = count ?: 0
    )
}