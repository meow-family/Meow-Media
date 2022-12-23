package com.octopus.socialnetwork.data.remote.pagingsource

import com.octopus.socialnetwork.data.remote.response.dto.post.PostDto
import com.octopus.socialnetwork.data.remote.service.SocialService
import com.octopus.socialnetwork.domain.mapper.posts.toPost
import com.octopus.socialnetwork.domain.model.post.Post
import javax.inject.Inject

class PostsDataSource @Inject constructor(
    private val service: SocialService,
    private val currentUserId: Int,
) : BasePagingSource<PostDto>() {

    override suspend fun getData(page: Int): List<PostDto> {
        return service.viewNewsFeed(currentUserId, page).result.posts
    }
}