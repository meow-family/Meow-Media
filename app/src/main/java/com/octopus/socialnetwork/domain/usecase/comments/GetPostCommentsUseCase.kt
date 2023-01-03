package com.octopus.socialnetwork.domain.usecase.comments

import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.comments.toComment
import com.octopus.socialnetwork.domain.model.comment.Comment
import com.octopus.socialnetwork.domain.usecase.authentication.FetchUserIdUseCase
import javax.inject.Inject

class GetPostCommentsUseCase @Inject constructor(
    private val socialRepository: SocialRepository,
    private val fetchUserId: FetchUserIdUseCase
) {
    private var page = 1
    suspend operator fun invoke(postId: Int): List<Comment> {
        val result = socialRepository.getCommentsPager(fetchUserId(), "post", postId, page)
        return if (result?.isNotEmpty() == true) {
            page += 1
            result.map { it.toComment() }
        } else {
            emptyList()
        }
    }
}