package com.octopus.socialnetwork.ui.util.fake_data

import com.octopus.socialnetwork.ui.screen.profile.uistate.UserDetailsUiState
import com.octopus.socialnetwork.ui.util.enums.UserRelationUiState

object FakeData {


    val meUserDetailsUiState = UserDetailsUiState(
        userId = 0,
        fullName = "Ameer Amjed",
        username = "AmeerAmjed",
        friendsCount = "0",
        profileAvatar = "",
        profileCover = "",
        relation = UserRelationUiState.ME
    )

    val friendUserDetailsUiState = UserDetailsUiState(
        userId = 0,
        fullName = "Ahmed Omer",
        username = "AhmedOmer",
        friendsCount = "0",
        profileAvatar = "",
        profileCover = "",
        relation = UserRelationUiState.IS_FRIEND
    )


    val notFriendUserDetailsUiState = UserDetailsUiState(
        userId = 0,
        fullName = "Ali Omer",
        username = "AliOmer",
        friendsCount = "0",
        profileAvatar = "",
        profileCover = "",
        relation = UserRelationUiState.NOT_FRIEND
    )

    val RequestedUserDetailsUiState = UserDetailsUiState(
        userId = 0,
        fullName = "Ali Omer",
        username = "AliOmer",
        friendsCount = "0",
        profileAvatar = "",
        profileCover = "",
        relation = UserRelationUiState.REQUESTED
    )
}