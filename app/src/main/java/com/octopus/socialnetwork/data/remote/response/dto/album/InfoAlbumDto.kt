package com.octopus.socialnetwork.data.remote.response.dto.album

import com.google.gson.annotations.SerializedName

class InfoAlbumDto(
    @SerializedName("guid")
    val albumId: Int?
)