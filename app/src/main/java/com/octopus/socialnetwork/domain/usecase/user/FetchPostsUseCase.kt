package com.octopus.socialnetwork.domain.usecase.user

import androidx.paging.PagingData
import androidx.paging.map
import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.posts.toPost
import com.octopus.socialnetwork.domain.model.post.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FetchPostsUseCase @Inject constructor(
    private val socialRepository: SocialRepository,
) {
    suspend operator fun invoke(visitedUserId: Int) : Flow<PagingData<Post>> {
        return socialRepository.getUserPostsPager(visitedUserId).flow.map { pager -> pager.map { it.toPost() } }
    }

}