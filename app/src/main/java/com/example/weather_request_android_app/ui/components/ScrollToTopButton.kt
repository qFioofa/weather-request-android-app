package com.example.weather_request_android_app.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun ScrollToTopButton(
    lazyListState: androidx.compose.foundation.lazy.LazyListState,
    threshold: Int = 10,
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()
    var isVisible by remember { mutableStateOf(false) }

    LaunchedEffect(lazyListState) {
        snapshotFlow { lazyListState.firstVisibleItemIndex }
            .collect { index ->
                isVisible = index > threshold
            }
    }

    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing)),
        exit = fadeOut(animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing))
    ) {
        FloatingActionButton(
            onClick = {
                scope.launch {
                    lazyListState.animateScrollToItem(0)
                }
            },
            modifier = modifier
                .padding(16.dp),
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ) {
            Icon(
                imageVector = Icons.Default.ArrowUpward,
                contentDescription = "Scroll to top"
            )
        }
    }
}