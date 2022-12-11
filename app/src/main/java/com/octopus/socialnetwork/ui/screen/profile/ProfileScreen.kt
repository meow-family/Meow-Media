package com.octopus.socialnetwork.ui.screen.profile
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.composable.ButtonFollow
import com.octopus.socialnetwork.ui.composable.Divider
import com.octopus.socialnetwork.ui.composable.SpaceHorizontally16dp
import com.octopus.socialnetwork.ui.composable.SpaceHorizontally4dp
import com.octopus.socialnetwork.ui.composable.SpaceHorizontally8dp
import com.octopus.socialnetwork.ui.composable.SpaceVertically10dp
import com.octopus.socialnetwork.ui.composable.SpaceVertically8dp
import com.octopus.socialnetwork.ui.composable.profile.ButtonMessage
import com.octopus.socialnetwork.ui.composable.profile.ProfileInformation
import com.octopus.socialnetwork.ui.composable.profile.ProfilePostItem
import com.octopus.socialnetwork.ui.screen.post.navigateToPostScreen
import com.octopus.socialnetwork.ui.screen.profile.uistate.ProfileUiState
import com.octopus.socialnetwork.ui.theme.PoppinsTypography
import com.octopus.socialnetwork.ui.theme.textSecondaryColor


@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()
    ProfileContent(
        state = state,
        onClickBack = { navController.popBackStack() },
        onClickFollow = viewModel::onClickFollow,
        onClickMessage = viewModel::onClickMessage,
        onClickPost = { postId, postOwnerId ->
            navController.navigateToPostScreen(postId, postOwnerId)
        }

    )
}

@Composable
private fun ProfileContent(
    state: ProfileUiState,
    onClickBack: () -> Unit,
    onClickFollow: () -> Unit,
    onClickMessage: () -> Unit,
    onClickPost: (Int, Int) -> Unit,
) {


    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item(span = { GridItemSpan(3) }) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White)
            ) {
                ProfileInformation(
                    backImageProfile = rememberAsyncImagePainter(model = state.profileCover),
                    profileImage = rememberAsyncImagePainter(model = state.profileAvatar),

                    )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(136.dp)
                ) {
                    Text(
                        text = state.fullName,
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        fontWeight = FontWeight.Bold,
                        fontFamily = PoppinsTypography.subtitle1.fontFamily,
                        fontStyle = PoppinsTypography.subtitle1.fontStyle,
                        fontSize = PoppinsTypography.subtitle1.fontSize,
                        color = MaterialTheme.colors.textSecondaryColor,
                        style = MaterialTheme.typography.h6,
                    )
                    Text(
                        text = state.username,
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        fontWeight = FontWeight.Light,
                        color = MaterialTheme.colors.onSecondary,
                        fontFamily = PoppinsTypography.caption.fontFamily,
                        fontStyle = PoppinsTypography.caption.fontStyle,
                        fontSize = PoppinsTypography.caption.fontSize

                    )

                    SpaceVertically10dp()
                    Row(modifier = Modifier.align(Alignment.CenterHorizontally))
                    {

                        Text(
                            text = state.friendsCount,
                            fontWeight = FontWeight.Bold,
                            fontFamily = PoppinsTypography.caption.fontFamily,
                            fontStyle = PoppinsTypography.caption.fontStyle,
                            fontSize = PoppinsTypography.caption.fontSize
                        )
                        SpaceHorizontally4dp()
                        Text(
                            text = stringResource(R.string.friends),
                            fontWeight = FontWeight.W400,
                            fontFamily = PoppinsTypography.caption.fontFamily,
                            fontStyle = PoppinsTypography.caption.fontStyle,
                            fontSize = PoppinsTypography.caption.fontSize
                        )
                        SpaceHorizontally16dp()
                        Text(
                            text = state.postCount,
                            fontWeight = FontWeight.Bold,
                            fontFamily = PoppinsTypography.caption.fontFamily,
                            fontStyle = PoppinsTypography.caption.fontStyle,
                            fontSize = PoppinsTypography.caption.fontSize
                        )
                        SpaceHorizontally4dp()
                        Text(
                            text = stringResource(R.string.posts),
                            fontWeight = FontWeight.W400,
                            fontFamily = PoppinsTypography.caption.fontFamily,
                            fontStyle = PoppinsTypography.caption.fontStyle,
                            fontSize = PoppinsTypography.caption.fontSize
                        )


                    }
                    SpaceVertically8dp()
                    Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                        ButtonFollow(onFollow = onClickFollow)
                        SpaceHorizontally8dp()
                        ButtonMessage(onMessage = onClickMessage)
                    }

                }
                Divider()
            }
        }

        items(items = state.profilePosts) { ProfilePostUiState ->
            ProfilePostItem(
                post = ProfilePostUiState,
                onClickPost = onClickPost
            )
        }
    }

}