package com.octopus.socialnetwork.data.remote.response.dto.photo.photoDetails


import com.google.gson.annotations.SerializedName

data class PhotoDTO(
    @SerializedName("album")
    val album: Album,
    @SerializedName("photo")
    val photo: Photo
)