package com.octopus.socialnetwork.domain.model.notifications

data class Group(
    val guid: Int,
    val title: String,
    val isMember: Boolean,
)