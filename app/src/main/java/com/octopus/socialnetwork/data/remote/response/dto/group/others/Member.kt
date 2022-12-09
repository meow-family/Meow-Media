package com.octopus.socialnetwork.data.remote.response.dto.group.others

import com.google.gson.annotations.SerializedName

data class Member(
    @SerializedName("birthdate")
    val birthdate: String,
    @SerializedName("cover_url")
    val coverUrl: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("fullname")
    val fullName: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("guid")
    val currentUserId: Int,
    @SerializedName("icon")
    val icon: Icon,
    @SerializedName("language")
    val language: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("username")
    val username: String
)