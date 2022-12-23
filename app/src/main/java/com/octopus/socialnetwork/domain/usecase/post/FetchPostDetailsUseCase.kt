package com.octopus.socialnetwork.domain.usecase.post

import android.content.Context
import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.posts.toPost
import com.octopus.socialnetwork.domain.model.post.Post
import com.octopus.socialnetwork.domain.usecase.user.FetchUserIdUseCase
import com.octopus.socialnetwork.ui.util.extensions.blockBadWordsInText
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class FetchPostDetailsUseCase @Inject constructor(
    private val socialRepository: SocialRepository,
    private val fetchUserIdUseCase: FetchUserIdUseCase,
    @ApplicationContext private val Context: Context,
) {
    suspend operator fun invoke(postId: Int): Post {
        val postData = socialRepository.viewPost(postId, fetchUserIdUseCase()).toPost()
            return postData.apply {
                this.description = blockBadWordsInText(postData.description, Context)
            }
    }
}