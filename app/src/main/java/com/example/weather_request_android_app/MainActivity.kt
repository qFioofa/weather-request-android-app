package com.example.weather_request_android_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weather_request_android_app.ui.components.*
import com.example.weather_request_android_app.ui.viewmodel.MainViewModel
import com.example.weather_request_android_app.ui.theme.MyApplicationTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = remember { MainViewModel(this) }
            val isDark by viewModel.isDarkTheme.collectAsState()
            val selectedCity by viewModel.selectedCity.collectAsState()
            val selectedDays by viewModel.selectedDays.collectAsState()

            MyApplicationTheme(darkTheme = isDark) {
                val lazyListState = rememberLazyListState()
                val scope = rememberCoroutineScope()
                var showAbout by remember { mutableStateOf(false) }

                if (showAbout) {
                    AboutDialog(onDismiss = { showAbout = false })
                }

                Scaffold(
                    topBar = {
                        WeatherHeader(
                            isDarkTheme = isDark,
                            onScrollToTop = {
                                scope.launch {
                                    lazyListState.animateScrollToItem(0)
                                }
                            },
                            onToggleTheme = { viewModel.toggleTheme() },
                            onShowInfo = { showAbout = true }
                        )
                    },
                    content = { innerPadding ->
                        LazyColumn(
                            state = lazyListState,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(innerPadding)
                        ) {
                            item {
                                WeatherForm(
                                    selectedCity = selectedCity,
                                    selectedDays = selectedDays,
                                    onCityChange = { viewModel.updateCity(it) },
                                    onDaysChange = { viewModel.updateDays(it) },
                                    onSubmit = { viewModel.openWeatherInBrowser(this@MainActivity) }
                                )
                            }
                            item {
                                AllCities(
                                    onCitySelected = { cityName ->
                                        viewModel.updateCity(cityName)
                                        scope.launch {
                                            lazyListState.animateScrollToItem(0)
                                        }
                                    }
                                )
                            }
                        }
                    },
                    floatingActionButton = {
                        ScrollToTopButton(
                            lazyListState = lazyListState,
                            modifier = Modifier
                        )
                    }
                )
            }
        }
    }
}