package com.octopus.socialnetwork.ui.screen.profile
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.composable.CircleButton
import com.octopus.socialnetwork.ui.composable.Loading
import com.octopus.socialnetwork.ui.composable.ReduceButton
import com.octopus.socialnetwork.ui.composable.SpaceHorizontally8dp
import com.octopus.socialnetwork.ui.composable.SpaceVertically24dp
import com.octopus.socialnetwork.ui.composable.profile.ProfilePostItem
import com.octopus.socialnetwork.ui.composable.profile.TabContentProfile
import com.octopus.socialnetwork.ui.composable.profile.UserDetails
import com.octopus.socialnetwork.ui.screen.post.navigateToPostScreen
import com.octopus.socialnetwork.ui.screen.profile.uistate.ProfileUiState
import com.octopus.socialnetwork.ui.theme.spacingMedium
import com.octopus.socialnetwork.ui.theme.spacingSmall


@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()

    ProfileContent(
        state = state,
        onClickAddFriend = viewModel::onClickAddFriend,
        onClickMessage = viewModel::onClickMessage,
        onChangeTabIndex = viewModel::onChangeTabIndex,
        onClickLogout = viewModel::onClickLogout,
        onClickEditeProfile = viewModel::onClickEditeProfile,
        onClickBack = { navController.popBackStack() },
        onClickPost = { postId, postOwnerId ->
            navController.navigateToPostScreen(postId, postOwnerId)
        },
    )
}

@Composable
private fun ProfileContent(
    state: ProfileUiState,
    onClickBack: () -> Unit,
    onClickAddFriend: () -> Unit,
    onClickMessage: () -> Unit,
    onChangeTabIndex: (index: Int) -> Unit,
    onClickPost: (Int, Int) -> Unit,
    onClickLogout: () -> Unit,
    onClickEditeProfile: () -> Unit,
) {

    if (state.isLoading) {
        Loading()
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(spacingMedium),
            verticalArrangement = Arrangement.spacedBy(spacingSmall),
            horizontalArrangement = Arrangement.spacedBy(spacingSmall)
        ) {
            item(span = { GridItemSpan(3) }) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colors.background)
                ) {
                    UserDetails(
                        state = state.userDetails
                    )


                    Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                        if (state.isUserVisitor) ReduceButton(
                            onClick = onClickAddFriend,
                            isSelected = state.isRequestSent,
                            idTitleResource = if (state.isRequestSent) R.string.requested else R.string.add_friend,
                            idIconResource = R.drawable.add_person,
                        ) else
                            ReduceButton(
                                onClick = onClickEditeProfile,
                                idTitleResource = R.string.edit_profile,
                                idIconResource = R.drawable.edite_profile,
                            )
                        SpaceHorizontally8dp()
                        if (state.isUserVisitor) CircleButton(
                            onClick = onClickMessage,
                            idIconResource = R.drawable.massage,
                            idTitleResource = R.string.send_message
                        ) else CircleButton(
                            onClick = onClickLogout,
                            idIconResource = R.drawable.logout,
                            idTitleResource = R.string.logout
                        )
                    }

                    SpaceVertically24dp()

                }


            }

            item(span = { GridItemSpan(3) }) {
                TabContentProfile(
                    state = state.profileContentTab,
                    onChangeTabIndex = onChangeTabIndex
                )
            }

            if (state.profileContentTab.selectedTabIndex == 0) {
                items(items = state.profilePosts) { ProfilePostUiState ->
                    ProfilePostItem(
                        post = ProfilePostUiState,
                        onClickPost = onClickPost
                    )
                }
            } else {
                item {
                    Text(text = "Albums", color = Color.Blue)
                }
            }

        }

    }

}