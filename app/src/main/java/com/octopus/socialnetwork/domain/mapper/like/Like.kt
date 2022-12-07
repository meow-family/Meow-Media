package com.octopus.socialnetwork.domain.mapper.like

import com.octopus.socialnetwork.data.remote.response.dto.like.LikeDto
import com.octopus.socialnetwork.domain.model.like.Like

fun LikeDto.toLike(): Like {
    return Like(
        count = count ?: 0
    )
}