package com.octopus.socialnetwork.domain.usecase.post

import android.content.Context
import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.posts.toPost
import com.octopus.socialnetwork.domain.model.post.Post
import com.octopus.socialnetwork.domain.usecase.user.FetchUserIdUseCase
import com.octopus.socialnetwork.ui.util.extensions.blockBadWordsInText
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import javax.inject.Inject

class CreatePostUseCase @Inject constructor(
    private val fetchUserIdUseCase: FetchUserIdUseCase,
    private val socialRepository: SocialRepository,
    @ApplicationContext private val context: Context
){
    suspend operator fun invoke(description: String, photo: File?) : Post?{
        val currentUserId = fetchUserIdUseCase()
        return photo?.let {
            socialRepository.createPost(
                currentUserId,
                currentUserId,
                blockBadWordsInText(description,context),
                POST_TYPE,
                it
            )?.toPost() ?: throw Exception("Adding post failed!")
        }
    }

    companion object{
        const val POST_TYPE = "user"
    }
}