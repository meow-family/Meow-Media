package com.octopus.socialnetwork.domain.usecase.user

import javax.inject.Inject

class FetchUserPostsCountUseCase @Inject constructor(
    private val fetchUserPosts: FetchUserPostsUseCase
) {
    suspend operator fun invoke(guid:Int, uguid: Int): Int {
        return fetchUserPosts(guid,uguid).count
    }
}