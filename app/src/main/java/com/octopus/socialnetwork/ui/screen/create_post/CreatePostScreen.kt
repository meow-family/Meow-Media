package com.octopus.socialnetwork.ui.screen.create_post

import android.net.Uri
import android.os.Build
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.composable.LoadingDialog
import com.octopus.socialnetwork.ui.composable.backgroundTextShadow
import com.octopus.socialnetwork.ui.composable.register.CustomDialog
import com.octopus.socialnetwork.ui.screen.create_post.state.CreatePostUiState
import com.octopus.socialnetwork.ui.screen.main.navigateToMain
import com.octopus.socialnetwork.ui.theme.LightBlack_65
import com.octopus.socialnetwork.ui.theme.Shapes
import com.octopus.socialnetwork.ui.theme.spacingMedium
import com.octopus.socialnetwork.ui.composable.buttom_navigation_bar.FloatingActionButton as FloatingAction

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CreatePostScreen(
    navController: NavController,
    viewModel: CreatePostViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()


    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri -> uri?.let { viewModel.setImageUri(it) } }
    )

    CreatePostContent(
        state = state,
        navController = navController,
        singlePhotoPickerLauncher = singlePhotoPickerLauncher,
        onChangeCaptionText = viewModel::onChangeCaptionText,
        onClickAddImage = viewModel::onClickAddImage,
        onInvalidImageDetection = viewModel::onInvalidImageDetection,
        onClickBack = { navController.popBackStack() },
        onClickChangeImage = viewModel::onClickChangeImage,
    )
}

@Composable
fun CreatePostContent(
    state: CreatePostUiState,
    navController: NavController,
    singlePhotoPickerLauncher: ManagedActivityResultLauncher<PickVisualMediaRequest, Uri?>,
    onClickChangeImage: (Uri) -> Unit,
    onClickBack: () -> Unit,
    onClickAddImage: () -> Unit,
    onInvalidImageDetection: () -> Unit,
    onChangeCaptionText: (String) -> Unit,
) {


    Box(
        modifier = Modifier
            .fillMaxSize()
            .zIndex(1f)
            .navigationBarsPadding()
            .imePadding()
            .verticalScroll(rememberScrollState(), reverseScrolling = true)
            .size(32.dp),
        contentAlignment = Alignment.TopEnd
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .zIndex(1f)
                .padding(spacingMedium),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            IconButton(
                onClick = { onClickBack() },
                Modifier
                    .clip(CircleShape)
                    .background(color = LightBlack_65)
                    .zIndex(1f)
                    .size(32.dp)

            ) {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = stringResource(id = R.string.back),
                    tint = Color.White
                )
            }
            Button(
                onClick = { state.imageUri?.let { imageUri -> onClickChangeImage(imageUri) } },
                modifier = Modifier
                    .width(120.dp)
                    .zIndex(1f)
                    .height(40.dp),
                shape = Shapes.large,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.primary
                ),
                enabled = state.imageUri != null
            ) {
                Text(
                    text = stringResource(id = R.string.post),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                )
            }
        }

        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = state.imageUri,
            contentScale = ContentScale.Crop,
            contentDescription = stringResource(id = R.string.image_post)
        )


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .align(alignment = Alignment.BottomCenter),
            horizontalAlignment = Alignment.End
        ) {
            FloatingAction(
                onClick = onClickAddImage,
                modifier = Modifier.padding(spacingMedium),
                imageVector = if (state.imageUri == null) Icons.Filled.Add else Icons.Filled.Edit,
                hiddenBoarder = true,
            )

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .backgroundTextShadow()
                    .padding(horizontal = spacingMedium),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.White,
                    disabledTextColor = Color.Transparent,
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent

                ),
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.add_caption),
                        color = Color.White
                    )
                },
                value = state.captionText,
                onValueChange = onChangeCaptionText
            )
        }


    }

    if (state.isLoading) {
        LoadingDialog()
    }
    if (state.isInvalidImage) {
        Dialog(onDismissRequest = { }) {
            CustomDialog(
                icon = Icons.Default.Image,
                title = stringResource(R.string.image_post_rejected),
                description = stringResource(R.string.image_post_rejected_description),
                actionTitle = stringResource(id = R.string.ok),
                checkAction = {
                    onInvalidImageDetection()
                },
            )
        }

    }
    LaunchedEffect(state.isAddNewImage) {
        singlePhotoPickerLauncher.launch(
            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
        )
    }

    LaunchedEffect(state.isSuccess) {
        if (state.isSuccess) {
            navController.navigateToMain()
        }
    }

}


