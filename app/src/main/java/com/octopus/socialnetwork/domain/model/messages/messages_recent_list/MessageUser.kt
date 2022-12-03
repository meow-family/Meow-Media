package com.octopus.socialnetwork.domain.model.messages.messages_recent_list

data class MessageUser(
    val userId:Int,
    val fullName :String,
    val userName :String,
    val avatar : Avatar,
)
