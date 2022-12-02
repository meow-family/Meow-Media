package com.octopus.socialnetwork.domain.usecase.album

import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.album.asAlbumPhotos
import com.octopus.socialnetwork.domain.model.album.AlbumPhotos
import javax.inject.Inject

class FetchAlbumPhotosUseCase @Inject constructor(
    private val socialRepository: SocialRepository,
) {
    suspend operator fun invoke(uguid: Int, guid: Int): AlbumPhotos {
        return socialRepository.getPhotosList(uguid, guid).asAlbumPhotos()
    }
}