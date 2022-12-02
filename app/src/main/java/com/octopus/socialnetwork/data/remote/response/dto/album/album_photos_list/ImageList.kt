package com.octopus.socialnetwork.data.remote.response.dto.album.album_photos_list

import com.google.gson.annotations.SerializedName

data class ImageList(
    @SerializedName("guid")
    val guid: Int?,
    @SerializedName("image_url")
    val imageUrl: String?
)
