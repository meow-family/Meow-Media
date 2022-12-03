package com.octopus.socialnetwork.domain.usecase.user

import javax.inject.Inject

class FetchUserPostsCountUseCase @Inject constructor(
    private val fetchUserPosts: FetchUserPostsUseCase
) {
    suspend operator fun invoke(visitedUserId:Int, currentUserId: Int): Int {
        return fetchUserPosts(visitedUserId,currentUserId).count
    }
}