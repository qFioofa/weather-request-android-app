package com.example.weather_request_android_app.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherHeader(
	isDarkTheme: Boolean,
	onScrollToTop: () -> Unit,
	onToggleTheme: () -> Unit,
	onShowInfo: () -> Unit
) {
	TopAppBar(
		title = { Text("Weather App") },
		modifier = Modifier.height(64.dp),
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
					imageVector = if (isDarkTheme) Icons.Filled.CheckCircle else Icons.Filled.Check,
					contentDescription = "Toggle theme"
				)
			}
			IconButton(onClick = onShowInfo) {
				Icon(
					imageVector = Icons.Filled.Info,
					contentDescription = "About app"
				)
			}
		}
	)
}
