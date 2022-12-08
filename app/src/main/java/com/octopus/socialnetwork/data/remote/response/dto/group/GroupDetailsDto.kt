package com.octopus.socialnetwork.data.remote.response.dto.group

data class GroupDetailsDto(
    val coverurl: Boolean,
    val description: String,
    val guid: Int,
    val ismember: Boolean,
    val membership: String,
    val owner_guid: Int,
    val request_exists: Boolean,
    val subtype: String,
    val time_created: Int,
    val title: String,
    val total_requests: Int,
    val type: String
)