package com.example.weather_request_android_app.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.weather_request_android_app.utils.Constants
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun AllCities(
    onCitySelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val allCities = Constants.POPULAR_CITIES.map { it.name }
    var expanded by remember { mutableStateOf(false) }
    var selectedCityMessage by remember { mutableStateOf<String?>(null) }
    val scope = rememberCoroutineScope()

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "All Cities",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Button(
                onClick = { expanded = !expanded },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text(if (expanded) "Hide Cities" else "Show All Cities")
            }

            AnimatedVisibility(
                visible = selectedCityMessage != null,
                enter = fadeIn(animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing)),
                exit = fadeOut(animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing))
            ) {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Text(
                        text = "Выбран: ${selectedCityMessage ?: ""}",
                        modifier = Modifier.padding(12.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            if (expanded) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 300.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(allCities) { cityName ->
                        ListItem(
                            headlineContent = {
                                Text(
                                    text = cityName,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable(onClick = {
                                    selectedCityMessage = cityName
                                    onCitySelected(cityName)
                                    scope.launch {
                                        delay(1000)
                                        selectedCityMessage = null
                                    }
                                })
                        )
                    }
                }
            }
        }
    }
}