package com.octopus.socialnetwork.data.remote.response.dto.group.others

import com.octopus.socialnetwork.data.remote.response.dto.group.others.Icon

data class Member(
    val birthdate: String,
    val cover_url: String,
    val email: String,
    val first_name: String,
    val fullname: String,
    val gender: String,
    val guid: Int,
    val icon: Icon,
    val language: String,
    val last_name: String,
    val username: String
)