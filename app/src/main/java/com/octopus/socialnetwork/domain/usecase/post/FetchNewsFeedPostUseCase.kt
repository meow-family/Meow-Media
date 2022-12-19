package com.octopus.socialnetwork.domain.usecase.post

import com.octopus.socialnetwork.data.remote.pagingsource.PostsDataSource
import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.posts.toPost
import com.octopus.socialnetwork.domain.model.post.Post
import com.octopus.socialnetwork.domain.usecase.user.FetchUserIdUseCase
import javax.inject.Inject

class FetchNewsFeedPostUseCase @Inject constructor(
    private val socialRepository: SocialRepository,
    private val fetchUserIdUseCase: FetchUserIdUseCase,
) {
//    suspend operator fun invoke(): List<Post> {
//        return socialRepository.viewNewsFeed(fetchUserIdUseCase()).map {
//            it.toPost()
//        }.filter {
//            it.image.startsWith("https://")
//        }
//    }

     operator fun invoke(): PostsDataSource {
        return socialRepository.viewNewsFeedPagingSource(fetchUserIdUseCase())
    }
}