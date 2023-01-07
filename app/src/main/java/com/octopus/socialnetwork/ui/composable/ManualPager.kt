package com.octopus.socialnetwork.ui.composable

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.theme.LightBlack_65

@Composable
fun ManualPager(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colors.background,
    onRefresh: () -> Unit,
    isLoading: Boolean,
    error: String,
    reverseLayout: Boolean = false,
    isEndOfPager: Boolean,
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(16.dp),
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
        reverseLayout = reverseLayout,
        verticalArrangement = verticalArrangement,

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
            IconButton(onClick = onClickTryAgain, Modifier.fillMaxWidth()) {
                Icon(
                    imageVector = androidx.compose.material.icons.Icons.Default.Refresh ,
                    contentDescription = stringResource(R.string.try_again),
                    tint = MaterialTheme.colors.onSecondary,
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }
}