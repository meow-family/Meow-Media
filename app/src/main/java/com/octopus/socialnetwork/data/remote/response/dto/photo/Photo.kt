package com.octopus.socialnetwork.data.remote.response.dto.photo

import com.google.gson.annotations.SerializedName

data class Photo(
    @SerializedName("guid")
    val photoId: Int,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("is_liked_by_user")
    val isLikedByUser: Boolean,
    @SerializedName("time_created")
    val timeCreated: Int,
    @SerializedName("total_likes")
    val totalLikes: Int
)