package com.octopus.socialnetwork.data.remote.response.dto.comment


import com.google.gson.annotations.SerializedName
import com.octopus.socialnetwork.data.remote.response.dto.user.UserDto

data class CommentDto(
    @SerializedName("comments:post")
    val commentsPost: String?,
    @SerializedName("id")
    val commentId: Int?,
    @SerializedName("is_liked_by_user")
    val isLikedByUser: Boolean?,
    @SerializedName("owner_guid")
    val commentOwnerId: Int?,
    @SerializedName("subject_guid")
    val subjectId: Int?,
    @SerializedName("time_created")
    val timeCreated: Long?,
    @SerializedName("total_likes")
    val totalLikes: Int?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("user")
    val user: UserDto?
)