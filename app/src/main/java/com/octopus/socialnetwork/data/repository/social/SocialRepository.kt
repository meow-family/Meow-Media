package com.octopus.socialnetwork.data.repository.social

import com.octopus.socialnetwork.data.remote.response.dto.album.AlbumResponse
import com.octopus.socialnetwork.data.remote.response.dto.album.album_photos_list.AlbumPhotosDTO
import com.octopus.socialnetwork.data.remote.response.dto.album.user_list_albums.AlbumDTO
import com.octopus.socialnetwork.data.remote.response.dto.user.UserDetailsDTO
import com.octopus.socialnetwork.data.remote.response.dto.user.UserFriendsDTO
import com.octopus.socialnetwork.data.remote.response.dto.user.UserPostsDTO
import retrofit2.Response

interface SocialRepository {
    suspend fun getUserDetails(visitedUserId: Int): UserDetailsDTO
    suspend fun getUserFriends(visitedUserId: Int): UserFriendsDTO
    suspend fun getUserPosts(visitedUserId: Int, currentUserId: Int): UserPostsDTO

    suspend fun getUserListPhotos(visitedUserId: Int, currentUserId: Int): AlbumDTO
    suspend fun getPhotosList(visitedUserId: Int, currentUserId: Int): AlbumPhotosDTO
    suspend fun postPhotosAlbum(title: String, guid: Int, privacy: Int): Response<AlbumResponse>
    suspend fun deleteAlbumPhoto(photoid: Int, visitedUserId: Int): Response<AlbumResponse>
}