package com.octopus.socialnetwork.data.remote.response.dto.post


import com.google.gson.annotations.SerializedName
import com.octopus.socialnetwork.data.remote.response.dto.user.UserDetailsDTO

data class PostDTO(
    @SerializedName("guid")
    val postId: Int?,
    @SerializedName("time_created")
    val timeCreated: Long?,
    @SerializedName("time_updated")
    val timeUpdated: String?,
    @SerializedName("owner_guid")
    val ownerId: Int?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("subtype")
    val subType: String?,
    @SerializedName("item_type")
    val itemType: String?,
    @SerializedName("item_guid")
    val itemId: String?,
    @SerializedName("poster_guid")
    val posterId: String?,
    @SerializedName("access")
    val access: String?,
    @SerializedName("profile_photo_url")
    val profilePhotoUrl: String?,
    @SerializedName("last_three_reactions")
    val lastThreeReactions: String?,
    @SerializedName("total_likes")
    val totalLikes: Int?,
    @SerializedName("is_liked_by_user")
    val isLikedByUser: Boolean?,
    @SerializedName("total_comments")
    val totalComments: Int?,
    @SerializedName("friends")
    val friends: List<UserDetailsDTO>?,
    @SerializedName("text")
    val text: String?,
    @SerializedName("location")
    val location: String?,
    @SerializedName("user")
    val user: UserDetailsDTO?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("posted_user")
    val postedUser: UserDetailsDTO?,
    @SerializedName("code")
    val code: String?,
    @SerializedName("message")
    val message: String?,
    )