package com.octopus.socialnetwork.data.remote.response.dto.album

import com.google.gson.annotations.SerializedName

data class ImageList(
    @SerializedName("guid")
    val imageId: Int?,
    @SerializedName("image_url")
    val imageUrl: String?
)
