package com.octopus.socialnetwork.domain.model.massages.messagesRecentList

data class MessageDetails(
    val messageFrom:MessageSender,
    val messageTo :MessageSender,
    val message:String,
)