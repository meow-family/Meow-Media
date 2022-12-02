package com.octopus.socialnetwork.domain.model.massages.messagesRecentList

data class MessageDetails(
    val messageFrom:MessageFrom,
    val messageTo :MessageTo,
    val message:String,
)