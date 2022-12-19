package com.octopus.socialnetwork.domain.usecase.post

import android.util.Log
import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.posts.toPost
import com.octopus.socialnetwork.domain.model.post.Post
import com.octopus.socialnetwork.domain.usecase.user.FetchUserIdUseCase
import java.io.File
import javax.inject.Inject

class CreatePostUseCase @Inject constructor(
    private val fetchUserIdUseCase: FetchUserIdUseCase,
    private val socialRepository: SocialRepository
){
    suspend operator fun invoke(description: String, photo: File?) : Post?{
        val currentUserId = fetchUserIdUseCase()
        return photo?.let { socialRepository.createPost(currentUserId,currentUserId,"meow meow", POSTTYPE,it)?.toPost()?: throw Exception("meow!")}
    }

    companion object{
        const val POSTTYPE = "user"
    }
}