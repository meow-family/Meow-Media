package com.octopus.socialnetwork.data.repository.social

import com.octopus.socialnetwork.data.remote.response.dto.album.AlbumResponse
import com.octopus.socialnetwork.data.remote.response.dto.album.album_photos_list.AlbumPhotosDTO
import com.octopus.socialnetwork.data.remote.response.dto.album.user_list_albums.AlbumDTO
import com.octopus.socialnetwork.data.remote.response.dto.user.UserDetailsDTO
import com.octopus.socialnetwork.data.remote.response.dto.user.UserFriendsDTO
import com.octopus.socialnetwork.data.remote.response.dto.user.UserPostsDTO
import com.octopus.socialnetwork.data.remote.service.SocialService
import retrofit2.Response
import javax.inject.Inject

class SocialRepositoryImpl @Inject constructor(
    private val socialService: SocialService,
) : SocialRepository {

    override suspend fun getUserDetails(visitedUserId: Int): UserDetailsDTO {
        return socialService.getUserDetails(visitedUserId).payload
    }

    override suspend fun getUserFriends(visitedUserId: Int): UserFriendsDTO {
        return socialService.getUserFriends(visitedUserId).payload
    }

    override suspend fun getUserPosts(visitedUserId: Int, currentUserId: Int): UserPostsDTO {
        return socialService.getUserPosts(visitedUserId, currentUserId).payload
    }

    override suspend fun getUserListPhotos(visitedUserId: Int, currentUserId: Int): AlbumDTO {
        return socialService.getUserListPhotos(visitedUserId, currentUserId).payload
    }

    override suspend fun getPhotosList(visitedUserId: Int, currentUserId: Int): AlbumPhotosDTO {
        return socialService.getPhotosList(visitedUserId).payload
    }

    override suspend fun postPhotosAlbum(
        title: String,
        guid: Int,
        privacy: Int
    ): Response<AlbumResponse> {
        return socialService.postPhotosAlbum(title, guid, privacy)
    }

    override suspend fun deleteAlbumPhoto(
        photoid: Int,
        visitedUserId: Int
    ): Response<AlbumResponse> {
        return socialService.deleteAlbumPhoto(photoid, visitedUserId)
    }

}