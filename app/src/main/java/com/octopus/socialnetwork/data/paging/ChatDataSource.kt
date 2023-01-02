package com.octopus.socialnetwork.data.paging

import com.octopus.socialnetwork.data.remote.response.dto.messages.MessageDto
import com.octopus.socialnetwork.data.remote.service.apiService.SocialService
import com.octopus.socialnetwork.data.repository.authentication.AuthenticationRepository
import javax.inject.Inject
import kotlin.properties.Delegates

class ChatDataSource @Inject constructor(
    private val service: SocialService,
    private val authenticationRepository: AuthenticationRepository,
) : BasePagingSource<MessageDto>() {

    private var friendId by Delegates.notNull<Int>()

    fun setFriendID(friend: Int) {
        friendId = friend
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MessageDto> {
        val pageNumber = params.key ?: 1
        return try {
            val response = authenticationRepository.getUserId()
                ?.let { service.getMessagesList(it,friendId,pageNumber) }
            LoadResult.Page(
                data = response?.result?.messages?.reversed() ?: emptyList(),
                prevKey = null ,
                nextKey =if (response?.result?.messages?.isNotEmpty() == true)
                    response.result.offset?.plus(1) else null
            )
        } catch (e: Throwable) {
            LoadResult.Error(e)
        }
    }

}