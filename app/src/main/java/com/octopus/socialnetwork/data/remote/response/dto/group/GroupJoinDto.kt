package com.octopus.socialnetwork.data.remote.response.dto.group

import com.google.gson.annotations.SerializedName
import com.octopus.socialnetwork.data.remote.response.dto.group.others.Group

data class GroupJoinDto(
    @SerializedName("group")
    val group: Group
)
