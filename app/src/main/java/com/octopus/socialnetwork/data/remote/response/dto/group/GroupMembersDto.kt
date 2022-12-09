package com.octopus.socialnetwork.data.remote.response.dto.group

import com.google.gson.annotations.SerializedName
import com.octopus.socialnetwork.data.remote.response.dto.group.others.Member

data class GroupMembersDto(
    @SerializedName("count")
    val count: Int,
    @SerializedName("member")
    val members: List<Member>,
    @SerializedName("offset")
    val offset: Int
)