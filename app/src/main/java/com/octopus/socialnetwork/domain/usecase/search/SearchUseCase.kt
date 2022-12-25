package com.octopus.socialnetwork.domain.usecase.search

import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.search.toSearch
import com.octopus.socialnetwork.domain.model.search.Search
import com.octopus.socialnetwork.domain.usecase.authentication.FetchUserIdUseCase
import kotlinx.coroutines.flow.last
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val SocialRepository: SocialRepository,
    private val FetchUserIdUseCase: FetchUserIdUseCase
) {
    suspend operator fun invoke(query: String): Search {
        return SocialRepository.search(FetchUserIdUseCase(), query).toSearch()
    }
}