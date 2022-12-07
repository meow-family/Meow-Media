package com.octopus.socialnetwork.domain.usecase.album

import com.octopus.socialnetwork.data.remote.response.dto.album.AlbumPhotosDto
import com.octopus.socialnetwork.data.repository.social.SocialRepository
import javax.inject.Inject

class FetchAlbumPhotosUseCase @Inject constructor(
    private val socialRepository: SocialRepository,
) {
    suspend operator fun invoke(albumId: Int): AlbumPhotosDto {
        return socialRepository.getAlbumPhotos(albumId)
    }
}