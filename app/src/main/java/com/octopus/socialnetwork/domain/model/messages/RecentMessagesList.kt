package com.octopus.socialnetwork.domain.model.messages


data class RecentMessagesList(
    val massagesDetails : List<MessageDetails>,
    val count: Int,
)
