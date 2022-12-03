package com.octopus.socialnetwork.data.remote.response.dto.like


import com.google.gson.annotations.SerializedName

data class ReactionDTO(
    @SerializedName("like")
    val like: String
)