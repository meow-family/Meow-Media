package com.octopus.socialnetwork.data.remote.response.dto.album

import com.google.gson.annotations.SerializedName

class StateDto(
    @SerializedName("status")
    val status: Boolean?
)