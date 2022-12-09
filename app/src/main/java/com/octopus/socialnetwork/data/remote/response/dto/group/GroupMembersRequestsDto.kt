package com.octopus.socialnetwork.data.remote.response.dto.group

import com.google.gson.annotations.SerializedName
import com.octopus.socialnetwork.data.remote.response.dto.group.others.Request

data class GroupMembersRequestsDto(
    @SerializedName("request")
    val requests: List<Request>
)