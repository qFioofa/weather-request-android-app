package com.example.weather_request_android_app.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.weather_request_android_app.utils.City
import com.example.weather_request_android_app.utils.Constants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CitySelector(
    onCitySelected: (String) -> Unit,
    initialCity: String = "",
    modifier: Modifier = Modifier
) {
    var inputText by remember { mutableStateOf(initialCity) }

    LaunchedEffect(initialCity) {
        inputText = initialCity
    }

    var isExpanded by remember { mutableStateOf(false) }
    var hasFocus by remember { mutableStateOf(false) }
    val allCities = Constants.POPULAR_CITIES
    var filteredCities by remember { mutableStateOf(emptyList<City>()) }

    LaunchedEffect(inputText, hasFocus) {
        if (hasFocus && inputText.isNotEmpty()) {
            filteredCities = allCities.filter { city ->
                city.name.contains(inputText, ignoreCase = true) ||
                        city.localized.contains(inputText, ignoreCase = true)
            }
            isExpanded = filteredCities.isNotEmpty()
        } else {
            isExpanded = false
        }
    }

    Column(modifier = modifier) {
        OutlinedTextField(
            value = inputText,
            onValueChange = { newText ->
                inputText = newText
            },
            label = { Text("Введите город", color = MaterialTheme.colorScheme.primary) },
            leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = "Search")
            },
            trailingIcon = {
                if (inputText.isNotEmpty()) {
                    IconButton(onClick = {
                        inputText = ""
                        filteredCities = emptyList()
                        isExpanded = false
                    }) {
                        Icon(Icons.Default.Clear, contentDescription = "Clear")
                    }
                }
            },
            placeholder = {
                Text(text = "Например, Москва", color = MaterialTheme.colorScheme.onSurfaceVariant)
            },
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { focusState ->
                    hasFocus = focusState.hasFocus
                    if (!focusState.hasFocus) {
                        isExpanded = false
                    }
                },
        )

        if (isExpanded && filteredCities.isNotEmpty()) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.onSurface
                ),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 240.dp)
                ) {
                    items(filteredCities) { city ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .clickable {
                                    val formattedName = formatCityNameForUrl(city.name)
                                    inputText = city.name
                                    onCitySelected(formattedName)
                                    isExpanded = false
                                }
                                .padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = buildAnnotatedString {
                                    append("${city.name} ")
                                    withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onSurfaceVariant)) {
                                        append("(${city.localized})")
                                    }
                                },
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }
        } else if (inputText.isNotEmpty() && isExpanded && filteredCities.isEmpty()) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer,
                    contentColor = MaterialTheme.colorScheme.onErrorContainer
                ),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Text(
                    text = "Город не найден",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

private fun formatCityNameForUrl(name: String): String {
    return name.trim().lowercase().replace(" ", "-")
}