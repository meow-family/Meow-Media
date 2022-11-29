package com.octopus.socialnetwork.composable

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun SpaceHorizontally4dp(){
    Spacer(modifier = Modifier.width(4.dp))
}

@Composable
fun SpaceHorizontally8dp(){
    Spacer(modifier = Modifier.width(8.dp))
}

@Composable
fun SpaceHorizontally16dp(){
    Spacer(modifier = Modifier.width(16.dp))
}



@Composable
fun SpaceVertically8dp(){
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
fun SpaceVertically10dp(){
    Spacer(modifier = Modifier.height(10.dp))
}



