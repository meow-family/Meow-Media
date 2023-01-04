package com.octopus.socialnetwork.domain.usecase.comments

import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.comments.toComment
import com.octopus.socialnetwork.domain.model.comment.Comment
import com.octopus.socialnetwork.domain.usecase.authentication.FetchUserIdUseCase
import javax.inject.Inject

class AddCommentUseCase @Inject constructor(
    private val socialRepository: SocialRepository,
    private val fetchUserIdUseCase: FetchUserIdUseCase,
) {
    suspend operator fun invoke(postId : Int, comment: String,) : Comment {
        return socialRepository.addComment(postId,comment,fetchUserIdUseCase()).toComment()
    }
}