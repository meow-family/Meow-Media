package com.octopus.socialnetwork.domain.usecase.comments

import androidx.paging.PagingData
import androidx.paging.map
import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.comments.toComment
import com.octopus.socialnetwork.domain.model.comment.Comment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPostCommentsUseCase @Inject constructor(
    private val socialRepository: SocialRepository,
) {
    suspend operator fun invoke(postId: Int): Flow<PagingData<Comment>> {
        return socialRepository.getCommentsPager(postId).flow.map { pager -> pager.map { it.toComment() } }
    }
}