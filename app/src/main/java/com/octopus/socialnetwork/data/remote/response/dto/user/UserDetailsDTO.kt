package com.octopus.socialnetwork.data.remote.response.dto.user

import com.google.gson.annotations.SerializedName
import com.octopus.socialnetwork.data.remote.response.dto.auth.Avatar

data class UserDetailsDTO(
    @SerializedName("guid")
    val id: Int?,
    @SerializedName("first_name")
    val firstName: String?,
    @SerializedName("last_name")
    val lastName: String?,
    @SerializedName("fullname")
    val fullName: String?,
    @SerializedName("username")
    val username: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("birthdate")
    val birthDate: String?,
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("icon")
    val avatar: Avatar?,
    @SerializedName("cover_url")
    val coverUrl: String?,
    @SerializedName("language")
    val language: String?,
)
