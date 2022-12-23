package com.octopus.socialnetwork.domain.usecase.comments

import android.content.Context
import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.comments.toComment
import com.octopus.socialnetwork.domain.model.comment.Comment
import com.octopus.socialnetwork.domain.usecase.user.FetchUserIdUseCase
import com.octopus.socialnetwork.ui.util.extensions.blockBadWordsInText

import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AddCommentUseCase @Inject constructor(
    private val socialRepository: SocialRepository,
    private val fetchUserIdUseCase: FetchUserIdUseCase,
    @ApplicationContext private val context: Context,
) {
    suspend operator fun invoke(postId: Int, comment: String): Comment {
        return socialRepository.addComment(
            postId,
            blockBadWordsInText(comment, context),
            fetchUserIdUseCase()
        )
            .toComment()
    }

}