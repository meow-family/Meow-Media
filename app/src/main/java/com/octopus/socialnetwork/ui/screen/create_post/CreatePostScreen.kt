package com.octopus.socialnetwork.ui.screen.create_post

import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.OpenableColumns
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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.composable.backgroundTextShadow
import com.octopus.socialnetwork.ui.screen.create_post.state.CreatePostUiState
import com.octopus.socialnetwork.ui.screen.main.navigateToMain
import com.octopus.socialnetwork.ui.theme.LightBlack_65
import com.octopus.socialnetwork.ui.theme.Shapes
import com.octopus.socialnetwork.ui.theme.spacingMedium
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CreatePostScreen(
    navController: NavController,
    viewModel: CreatePostViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()


    val context = LocalContext.current
    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            uri?.let {
                viewModel.setImageUri(it)
            }
        }
    )

    CreatePostContent(
        state = state,
        navController = navController,
        singlePhotoPickerLauncher = singlePhotoPickerLauncher,
        onChangeCaptionText = viewModel::onChangeCaptionText,
        onClickBack = { navController.popBackStack() },
        onClickAddPost = {
            state.imageUri?.let {
                viewModel.onClickChangeImage(createFileFromContentUri(it, context))
            }
        },

        )
}

@Composable
fun CreatePostContent(
    state: CreatePostUiState,
    navController: NavController,
    singlePhotoPickerLauncher: ManagedActivityResultLauncher<PickVisualMediaRequest, Uri?>,
    onClickAddPost: () -> Unit,
    onClickBack: () -> Unit,
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
                onClick = onClickAddPost,
                modifier = Modifier
                    .width(120.dp)
                    .zIndex(1f)
                    .height(40.dp),
                shape = Shapes.large,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.primary
                ),
                enabled = state.isValidImage
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

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .align(alignment = Alignment.BottomCenter)
                .backgroundTextShadow()
        ) {

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
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

    LaunchedEffect(key1 = true) {
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

@RequiresApi(Build.VERSION_CODES.O)
private fun createFileFromContentUri(fileUri: Uri, context: Context): File {
    var fileName = ""
    fileUri.let { returnUri ->
        context.contentResolver.query(returnUri, null, null, null)
    }?.use { cursor ->
        val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        cursor.moveToFirst()
        fileName = cursor.getString(nameIndex)
    }

    val iStream = context.contentResolver.openInputStream(fileUri)!!
    val outputDir = context.cacheDir!!

    val outputFile = File(outputDir, fileName)
    copyStreamToFile(iStream, outputFile)
    iStream.close()
    return outputFile
}

private fun copyStreamToFile(inputStream: InputStream, outputFile: File) {
    inputStream.use { input ->
        val outputStream = FileOutputStream(outputFile)
        outputStream.use { output ->
            val buffer = ByteArray(4 * 1024)
            while (true) {
                val byteCount = input.read(buffer)
                if (byteCount < 0) break
                output.write(buffer, 0, byteCount)
            }
            output.flush()
        }
    }
}

