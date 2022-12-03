package com.octopus.socialnetwork.data.remote.response.dto.photo.photo_profile


import com.google.gson.annotations.SerializedName
import com.octopus.socialnetwork.data.remote.response.dto.photo.photoDetails.Photo
import com.octopus.socialnetwork.data.remote.response.dto.user.UserDTO

data class UserProfileDTO(
    @SerializedName("photo")
    val photo: Photo,
    @SerializedName("user")
    val user: UserDTO
)