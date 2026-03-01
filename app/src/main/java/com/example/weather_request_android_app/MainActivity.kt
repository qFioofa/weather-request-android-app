package com.example.weather_request_android_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weather_request_android_app.ui.components.WeatherHeader
import com.example.weather_request_android_app.ui.components.AboutDialog
import com.example.weather_request_android_app.ui.viewmodel.MainViewModel
import com.example.weather_request_android_app.ui.theme.MyApplicationTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			val viewModel = remember { MainViewModel(this) }
			val isDark by viewModel.isDarkTheme.collectAsState()

			MyApplicationTheme(darkTheme = isDark) {
				val lazyListState = rememberLazyListState()
				val coroutineScope = rememberCoroutineScope()
				var showAbout by remember { mutableStateOf(false) }

				if (showAbout) {
					AboutDialog(onDismiss = { showAbout = false })
				}

				LazyColumn(
					state = lazyListState,
					modifier = Modifier.fillMaxSize()
				) {
					item {
						WeatherHeader(
							isDarkTheme = isDark,
							onScrollToTop = {
								coroutineScope.launch {
									lazyListState.animateScrollToItem(0)
								}
							},
							onToggleTheme = { viewModel.toggleTheme() },
							onShowInfo = { showAbout = true }
						)
					}
					items(50) { index ->
						Text(
							text = "Weather Item $index",
							modifier = Modifier
								.fillMaxWidth()
								.padding(16.dp),
							style = MaterialTheme.typography.bodyMedium
						)
					}
				}
			}
		}
	}
}
