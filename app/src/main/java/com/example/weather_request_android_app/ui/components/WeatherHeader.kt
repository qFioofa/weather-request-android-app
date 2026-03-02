package com.example.weather_request_android_app.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherHeader(
    isDarkTheme: Boolean,
    onScrollToTop: () -> Unit,
    onToggleTheme: () -> Unit,
    onShowInfo: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Weather Forecast",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.SemiBold
                )
            )
        },
        navigationIcon = {
            IconButton(onClick = onScrollToTop) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowUp,
                    contentDescription = "Scroll to top"
                )
            }
        },
        actions = {
            IconButton(onClick = onToggleTheme) {
                Icon(
                    imageVector = if (isDarkTheme) Icons.Default.Brightness5 else Icons.Default.Brightness3,
                    contentDescription = if (isDarkTheme) "Switch to Light Mode" else "Switch to Dark Mode"
                )
            }
            IconButton(onClick = onShowInfo) {
                Icon(
                    imageVector = Icons.Filled.Info,
                    contentDescription = "About app"
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
            titleContentColor = MaterialTheme.colorScheme.onSurface,
            navigationIconContentColor = MaterialTheme.colorScheme.primary,
            actionIconContentColor = MaterialTheme.colorScheme.primary
        )
    )
}