package com.octopus.socialnetwork.data.remote.response.dto.photo


import com.google.gson.annotations.SerializedName

data class PhotoDto(
    @SerializedName("album")
    val album: Album,
    @SerializedName("photo")
    val photo: Photo
)