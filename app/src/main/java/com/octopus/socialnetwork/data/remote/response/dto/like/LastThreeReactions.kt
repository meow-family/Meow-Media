package com.octopus.socialnetwork.data.remote.response.dto.like


import com.google.gson.annotations.SerializedName

data class LastThreeReactions(
    @SerializedName("like")
    val like: String
)