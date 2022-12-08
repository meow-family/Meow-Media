package com.octopus.socialnetwork.ui.screen.group

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.octopus.socialnetwork.ui.screen.group.uistate.GroupUiState
import com.octopus.socialnetwork.ui.theme.SocialNetworkTheme
import com.octopus.socialnetwork.ui.theme.spacingMedium

@Composable
@ExperimentalPagerApi
@ExperimentalMaterialApi
fun GroupScreen(
    navController: NavController,
    viewModel: GroupViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()


    GroupContent(
        state = state,
    )
}

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
private fun GroupContent(
    state: GroupUiState,
) {

    Column(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .padding(spacingMedium)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
    ) {


    }


}


@Preview
@Composable
@ExperimentalPagerApi
@ExperimentalMaterialApi
fun RegisterScreenPreview() {
    SocialNetworkTheme {
        Surface {

        }
    }
}