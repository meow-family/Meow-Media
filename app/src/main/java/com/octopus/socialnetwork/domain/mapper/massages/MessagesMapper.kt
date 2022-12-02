package com.octopus.socialnetwork.domain.mapper.massages

import com.octopus.socialnetwork.data.remote.response.dto.massages.RecentmessagesListDto.*
import com.octopus.socialnetwork.domain.model.massages.messagesRecentList.*

fun RecentMessagesListDTO.asRecentMassagesList(): RecentMessagesList {
    return RecentMessagesList(
        massagesDetails = payloadDTO?.list?.map { it.asRecentMassagesDetailsList() } ?: emptyList(),
    )
}

fun MessageDetailsDTO.asRecentMassagesDetailsList(): MessageDetails {
    return MessageDetails(
        message = message ?: "",
        messageFrom = messageFrom?.asMessageFrom() ?: MessageFrom(" ", " ", IconSizes(" ")),
        messageTo = messageTo?.asMessageTo() ?: MessageTo(" ", " ", IconSizes(" "))

    )
}

fun MessageToDTO.asMessageTo(): MessageTo {
    return MessageTo(
        fullName = fullName ?: "",
        userName = username ?: "",
        icon = (icon?.asIcon() ?: "") as IconSizes
    )
}

fun MessageFromDTO.asMessageFrom(): MessageFrom {
    return MessageFrom(
        fullName = fullName ?: "",
        userName = username ?: "",
        icon = (icon?.asIcon() ?: "") as IconSizes
    )
}

fun IconDTO.asIcon(): IconSizes {
    return IconSizes(
        linkOfSmallImage = small ?: " "
    )
}