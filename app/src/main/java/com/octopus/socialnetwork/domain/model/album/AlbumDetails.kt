package com.octopus.socialnetwork.domain.model.album

data class AlbumDetails(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val description: String,
    val privacy: String,
    val timeCreated: Int,
    val type: String,
    val subtype: String,
    val fileOSSNPhoto: String,
    val data: Data,
) {
    class Data
}
