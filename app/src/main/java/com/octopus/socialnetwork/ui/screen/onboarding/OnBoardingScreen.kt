package com.octopus.socialnetwork.ui.screen.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.composable.CustomButton
import com.octopus.socialnetwork.ui.composable.TextWithAction


@Composable
fun OnBoardingScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(R.drawable.ic_onboarding),
            contentDescription = stringResource(R.string.icon_on_boarding),
            modifier = Modifier.padding(top = 64.dp)
        )

        Text(
            text = stringResource(R.string.on_boarding_text), textAlign = TextAlign.Start,
            color = MaterialTheme.colors.onBackground, style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(top = 42.dp, start = 16.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        CustomButton(
            text = stringResource(R.string.create_account),
            onClick = {}
        )
        TextWithAction(
            text = stringResource(R.string.already_have_an_account),
            textAction = stringResource(R.string.login),
            onClick = {}
        )

    }

}

@Preview(showSystemUi = true)
@Composable
fun OnBoardingPreview() {
    OnBoardingScreen()
}