package com.octopus.socialnetwork.domain.usecase.search

import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.search.toSearch
import com.octopus.socialnetwork.domain.model.search.SearchResult
import com.octopus.socialnetwork.domain.usecase.authentication.FetchUserIdUseCase
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val SocialRepository: SocialRepository,
    private val fetchUserIdUseCase: FetchUserIdUseCase
) {
    suspend operator fun invoke(query: String): SearchResult {
        return if (query.isNotEmpty()) {
             SocialRepository.search(fetchUserIdUseCase(), query).toSearch()
        } else SearchResult()
    }
}