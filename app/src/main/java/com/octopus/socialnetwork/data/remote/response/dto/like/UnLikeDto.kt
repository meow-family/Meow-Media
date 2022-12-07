package com.octopus.socialnetwork.data.remote.response.dto.like


import com.google.gson.annotations.SerializedName

data class UnLikeDto(
    @SerializedName("count")
    val count: Boolean?,
    @SerializedName("last_three_reactions")
    val lastThreeReactions: Boolean?
)