package com.octopus.socialnetwork.data.remote.response.dto.user


import com.google.gson.annotations.SerializedName
import com.octopus.socialnetwork.data.remote.response.dto.Avatar


data class UserEditDTO(
    @SerializedName("birthdate")
    val birthDate: String?,
    @SerializedName("cover_url")
    val coverUrl: Boolean?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("first_name")
    val firstName: String?,
    @SerializedName("fullname")
    val fullName: String?,
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("guid")
    val userId: Int?,
    @SerializedName("icon")
    val icon: Avatar?,
    @SerializedName("language")
    val language: String?,
    @SerializedName("last_name")
    val lastName: String?,
    @SerializedName("username")
    val userName: String?
)