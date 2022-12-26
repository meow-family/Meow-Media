package com.octopus.socialnetwork.domain.usecase.post

import androidx.paging.PagingData
import androidx.paging.filter
import androidx.paging.map
import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.posts.toPost
import com.octopus.socialnetwork.domain.model.post.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class FetchPostsUseCase
@Inject constructor(
    private val socialRepository: SocialRepository,
) {
    operator fun invoke(): Flow<PagingData<Post>> {
        return socialRepository.getNewsFeedPager().flow.map { it.map { postEntity -> postEntity.toPost() }.filter {post ->
            post.description != "null:data"
        } }
    }
}