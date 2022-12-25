package com.octopus.socialnetwork.domain.usecase.comments

import androidx.paging.PagingData
import androidx.paging.map
import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.comments.toComment
import com.octopus.socialnetwork.domain.mapper.posts.toPost
import com.octopus.socialnetwork.domain.model.comment.Comment
import com.octopus.socialnetwork.domain.model.post.Post
import com.octopus.socialnetwork.domain.usecase.authentication.FetchUserIdUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPostCommentsUseCase @Inject constructor(
    private val socialRepository: SocialRepository,
    private val fetchUserIdUseCase: FetchUserIdUseCase,
) {
//    suspend operator fun invoke(postId: Int, type: String) : List<Comment>{
//        return socialRepository.getComments(fetchUserIdUseCase().last(), postId, type).map { it.toComment() }
//    }
    operator fun invoke(postId: Int): Flow<PagingData<Comment>> {
        return socialRepository.getCommentListPager(postId).flow.map { it.map { commentEntity -> commentEntity.toComment() } }
    }
}