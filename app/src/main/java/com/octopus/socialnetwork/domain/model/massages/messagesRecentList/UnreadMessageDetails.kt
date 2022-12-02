package com.octopus.socialnetwork.domain.model.massages.messagesRecentList

data class UnreadMessageDetails(
    val ifUnread:Boolean,
    val withUser :MessageSender
)
