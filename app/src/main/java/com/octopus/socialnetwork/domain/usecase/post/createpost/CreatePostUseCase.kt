package com.octopus.socialnetwork.domain.usecase.post.createpost

import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.posts.toPost
import com.octopus.socialnetwork.domain.model.post.Post
import com.octopus.socialnetwork.domain.usecase.authentication.FetchUserIdUseCase
import com.octopus.socialnetwork.domain.utils.Constants.ADDING_POST_FAILED
import com.octopus.socialnetwork.domain.utils.Constants.POST_TYPE
import kotlinx.coroutines.flow.first
import java.io.File
import javax.inject.Inject

class CreatePostUseCase @Inject constructor(
    private val fetchUserIdUseCase: FetchUserIdUseCase,
    private val socialRepository: SocialRepository
){
    suspend operator fun invoke(description: String, photo: File?) : Post?{
        val myUserId = fetchUserIdUseCase().first()
        return photo?.let {
            socialRepository.createPost(
                myUserId,
                myUserId,
                description,
                POST_TYPE,
                it
            )?.toPost() ?: throw Exception(ADDING_POST_FAILED)
        }
    }


}