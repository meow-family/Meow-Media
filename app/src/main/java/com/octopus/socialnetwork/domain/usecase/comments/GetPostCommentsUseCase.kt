package com.octopus.socialnetwork.domain.usecase.comments

import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.comments.toComment
import com.octopus.socialnetwork.domain.model.comment.Comment
import javax.inject.Inject

class GetPostCommentsUseCase @Inject constructor(
    private val socialRepository: SocialRepository,
) {
    suspend operator fun invoke(currentUserId: Int, postId: Int, type: String) : List<Comment>{
        return socialRepository.getComments(currentUserId, postId, type).map { it.toComment() }
    }
}