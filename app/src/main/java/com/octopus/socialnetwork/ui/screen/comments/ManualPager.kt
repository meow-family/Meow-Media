package com.octopus.socialnetwork.ui.screen.comments

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.octopus.socialnetwork.R

@Composable
fun ManualPager(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colors.background,
    onRefresh: () -> Unit,
    isLoading: Boolean,
    error: String,
    isEndOfPager: Boolean,
    contentPadding: PaddingValues,
    content: LazyListScope.() -> Unit,
) {
    val scrollState = rememberLazyListState()

    LazyColumn(
        modifier = modifier
            .background(backgroundColor)
            .fillMaxSize(),
        state = scrollState,
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(16.dp),

    ) {
        content()
        item {
            PagerStatusItem(
                isLoading = isLoading,
                error = error,
                isEndOfPager = isEndOfPager,
                onClickTryAgain = onRefresh
            )
        }
    }

    if (scrollState.isScrollingUp().not() || isEndOfPager.not()) {
        LaunchedEffect(key1 = scrollState.isScrollInProgress) {
            if (isLoading.not() && isEndOfPager.not()) {
                Log.e("PAGING","Scrolling DOWN")
                onRefresh()
            }
        }
    }
}

@Composable
private fun LazyListState.isScrollingUp(): Boolean {
    var previousIndex by remember(this) { mutableStateOf(firstVisibleItemIndex) }
    var previousScrollOffset by remember(this) { mutableStateOf(firstVisibleItemScrollOffset) }
    return remember(this) {
        derivedStateOf {
            if (previousIndex != firstVisibleItemIndex) {
                previousIndex > firstVisibleItemIndex
            } else {
                previousScrollOffset >= firstVisibleItemScrollOffset
            }.also {
                previousIndex = firstVisibleItemIndex
                previousScrollOffset = firstVisibleItemScrollOffset
            }
        }
    }.value
}


@Composable
fun PagerStatusItem(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    error: String,
    isEndOfPager: Boolean,
    onClickTryAgain: () -> Unit
) {
    Row(
        modifier = modifier.padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = MaterialTheme.colors.primary)
            }
        } else if (error.isNotEmpty()) {
            Text(modifier = Modifier.weight(1f), text = error)
            Button(onClick = { onClickTryAgain() }) {
                Text(
                    text = stringResource(id = R.string.try_again),
                    color = Color.White
                )
            }

        } else if (isEndOfPager) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.no_items),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colors.secondaryVariant,
                fontSize = 14.sp
            )
        }
    }
}