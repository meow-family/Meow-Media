package com.octopus.socialnetwork.domain.usecase.album

import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.album.asAlbum
import com.octopus.socialnetwork.domain.model.album.UserAlbum
import javax.inject.Inject

class FetchUserListPhotosUseCase @Inject constructor(
    private val socialRepository: SocialRepository,
) {
    suspend operator fun invoke(uguid: Int, guid: Int): UserAlbum {
        return socialRepository.getUserListPhotos(uguid, guid).asAlbum()
    }
}