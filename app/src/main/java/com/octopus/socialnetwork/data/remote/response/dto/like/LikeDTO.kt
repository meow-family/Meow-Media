package com.octopus.socialnetwork.data.remote.response.dto.like


import com.google.gson.annotations.SerializedName

data class LikeDTO(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("last_three_reactions")
    val lastThreeReactions: ReactionDTO?
)