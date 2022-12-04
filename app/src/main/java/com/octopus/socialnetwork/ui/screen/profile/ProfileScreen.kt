package com.octopus.socialnetwork.ui.screen.profile
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.composable.*
import com.octopus.socialnetwork.ui.composable.profile.ButtonFollow
import com.octopus.socialnetwork.ui.composable.profile.ButtonMessage
import com.octopus.socialnetwork.ui.composable.profile.ProfileInformation
import com.octopus.socialnetwork.ui.composable.profile.ProfilePostItem
import com.octopus.socialnetwork.ui.screen.profile.uistate.ProfileUiState
import com.octopus.socialnetwork.ui.theme.PoppinsTypography


@Preview(showSystemUi = true)
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()
    ProfileContent(
        state = state,
        onClickFollow = viewModel::onClickFollow,
        onClickMessage = viewModel::onClickMessage
    )
}

@Composable
private fun ProfileContent(
    state: ProfileUiState,
    onClickFollow: () -> Unit,
    onClickMessage: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {

        ProfileInformation(
            backImageProfile = rememberAsyncImagePainter(model = state.profileCover),
            profileImage = rememberAsyncImagePainter(model = state.profileAvatar)
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
                fontSize = PoppinsTypography.subtitle1.fontSize
            )
            Text(
                text = state.username,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontWeight = FontWeight.Light,
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

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ){
            items(items = state.profilePosts){
                ProfilePostItem(postImage = rememberAsyncImagePainter(model = it.postImage))
            }
        }

    }


}