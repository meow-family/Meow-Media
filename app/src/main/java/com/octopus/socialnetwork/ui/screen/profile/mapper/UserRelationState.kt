package com.octopus.socialnetwork.ui.screen.profile.mapper

import com.octopus.socialnetwork.domain.enums.UserRelation
import com.octopus.socialnetwork.ui.util.UserRelationUiState


fun UserRelation.toUserRelationUiState(): UserRelationUiState {
    return when (this) {
        UserRelation.ME -> UserRelationUiState.ME
        UserRelation.IS_FRIEND -> UserRelationUiState.IS_FRIEND
        UserRelation.REQUESTED -> UserRelationUiState.REQUESTED
        else -> UserRelationUiState.NOT_FRIEND
    }
}
