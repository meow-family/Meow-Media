package com.octopus.socialnetwork.domain.usecase.search

import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.search.toSearch
import com.octopus.socialnetwork.domain.model.search.Search
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val SocialRepository: SocialRepository,
) {
    suspend operator fun invoke(
        currentUserId: Int,
        typeOfSearch: String,
        query: String
    ): Search {
        return SocialRepository.search(currentUserId, typeOfSearch, query)
            .toSearch()
    }
}