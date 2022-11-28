package com.octopus.socialnetwork.ui.screen.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.octopus.socialnetwork.R

@Composable
fun OnBoardingScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(R.drawable.ic_onboarding),
            contentDescription = stringResource(R.string.icon_on_boarding),
            modifier = Modifier.padding(top = 64.dp)
        )

        Text(text = stringResource(R.string.on_boarding_text), textAlign = TextAlign.Center,
            color = MaterialTheme.colors.onBackground, style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(top = 42.dp))

        Spacer(modifier = Modifier.weight(1f))

        Button(onClick = {}, modifier = Modifier
            .fillMaxWidth()
            .height(44.dp),
            shape = RoundedCornerShape(22.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
            elevation = ButtonDefaults.elevation(0.dp)
        ) {
            Text(text = stringResource(R.string.create_account),
                color = MaterialTheme.colors.background,
                style = MaterialTheme.typography.button)
        }

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = stringResource(R.string.already_have_an_account),
                color = MaterialTheme.colors.onBackground,
                style = MaterialTheme.typography.caption)

            Text(text = stringResource(R.string.login), color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.body2)
        }

    }

}