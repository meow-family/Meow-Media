package com.octopus.socialnetwork.ui.screen.post

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.octopus.socialnetwork.ui.composable.post.LargPostDetails
import com.octopus.socialnetwork.ui.composable.post.PostAction
import com.octopus.socialnetwork.ui.composable.post.PostImage
import com.octopus.socialnetwork.ui.theme.Transparent


@Composable
fun PostScreen(
    viewModel: PostViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    PostContent(
        state = state,
        like = viewModel::like,
        comment = viewModel::comment,
        share = viewModel::share
    )

}

@Composable
fun PostContent(
    state: PostUiState,
    like: () -> Unit,
    comment: () -> Unit,
    share: () -> Unit
) {

    PostImage(postImage = rememberAsyncImagePainter(model = state.postImage))

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.End
    ) {


        Card(
            modifier = Modifier
                .height(300.dp)
                .width(72.dp),
            shape = RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp),
            backgroundColor = Transparent,
        ) {
            PostAction(
                likeCount = state.likeCount,
                commentCount = state.commentCount,
                like = like,
                comment = comment,
                share = share,
                modifier = Modifier
            )
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(color = Transparent)
        ) {
            LargPostDetails(
                profileImage = rememberAsyncImagePainter(model = state.profileImage),
                userName = state.userName,
                fullName = state.fullName,
                postDescription = state.postDescription
            )
        }
    }


}

@Preview(showSystemUi = true)
@Composable
fun PostScreenPreview() {
    PostScreen()
}