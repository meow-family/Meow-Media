package com.octopus.socialnetwork.data.repository.social

import com.octopus.socialnetwork.data.paging.BasePagingSource
import com.octopus.socialnetwork.data.remote.response.dto.notifications.NotificationItemsDto
import com.octopus.socialnetwork.data.remote.service.apiService.SocialService
import com.octopus.socialnetwork.data.repository.authentication.AuthenticationRepository
import javax.inject.Inject


class NotificationDataSource @Inject constructor(
    private val service: SocialService,
    private val authenticationRepository: AuthenticationRepository,
) : BasePagingSource<NotificationItemsDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NotificationItemsDto> {
        val pageNumber = params.key ?: 1
        return try {
            val response =
                authenticationRepository.getUserId()
                    ?.let { service.getUserNotifications(it, pageNumber) }
            LoadResult.Page(
                data = response?.result?.list ?: emptyList(),
                prevKey = null,
                nextKey = if (response?.result?.list?.isNotEmpty() == true) response.result.offset?.plus(
                    1
                ) else null
            )
        } catch (e: Throwable) {
            LoadResult.Error(e)
        }
    }
}