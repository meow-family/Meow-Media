package com.octopus.socialnetwork.domain.usecase.post.createpost

import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.posts.toPost
import com.octopus.socialnetwork.domain.model.post.Post
import com.octopus.socialnetwork.domain.usecase.authentication.FetchUserIdUseCase
import java.io.File
import javax.inject.Inject

class CreatePostUseCase @Inject constructor(
    private val fetchUserIdUseCase: FetchUserIdUseCase,
    private val socialRepository: SocialRepository
){
    suspend operator fun invoke(description: String, photo: File?) : Post?{
        val currentUserId = fetchUserIdUseCase()
        return photo?.let {
            socialRepository.createPost(
                currentUserId,
                currentUserId,
                description,
                POST_TYPE,
                it
            )?.toPost() ?: throw Exception("Adding post failed!")
        }
    }

    companion object{
        const val POST_TYPE = "user"
    }
}