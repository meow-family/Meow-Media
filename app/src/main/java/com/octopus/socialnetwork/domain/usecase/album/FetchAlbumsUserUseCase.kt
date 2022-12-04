package com.octopus.socialnetwork.domain.usecase.album

import com.octopus.socialnetwork.data.remote.response.dto.album.AlbumsDto
import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.album.asAlbum
import com.octopus.socialnetwork.domain.model.album.UserAlbum
import javax.inject.Inject

class FetchAlbumsUserUseCase @Inject constructor(
    private val socialRepository: SocialRepository,
) {
    suspend operator fun invoke(ownerAlbumsUserId: Int, visitedUserId: Int): AlbumsDto {
        return socialRepository.getAlbumsUser(ownerAlbumsUserId, visitedUserId)
    }
}