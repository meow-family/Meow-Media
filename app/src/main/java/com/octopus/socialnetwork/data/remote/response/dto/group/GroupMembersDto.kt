package com.octopus.socialnetwork.data.remote.response.dto.group

import com.octopus.socialnetwork.data.remote.response.dto.group.others.Member

data class GroupMembersDto(
    val count: Int,
    val members: List<Member>,
    val offset: Int
)