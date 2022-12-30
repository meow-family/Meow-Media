package com.octopus.socialnetwork.domain.model.notifications

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

data class Notifications(
    val notifications: List<NotificationItem>,
    val count: Int,
    val offset: Int,
)