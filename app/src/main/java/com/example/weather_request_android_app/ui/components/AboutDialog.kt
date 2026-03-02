package com.example.weather_request_android_app.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.weather_request_android_app.ui.theme.Comment
@Composable
fun AboutDialog(
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Card(
            shape = MaterialTheme.shapes.large,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .wrapContentHeight()
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Rounded.Cloud,
                    contentDescription = null,
                    modifier = Modifier.size(64.dp)
                )

                Text(
                    text = "About Weather App",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "A modern weather forecast application built with Jetpack Compose.\n\n" +
                            "• Version: 1.0\n" +
                            "• Platform: Android\n" +
                            "• Built with: Kotlin & Compose\n\n" +
                            "© 2026",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth()
                )

                Button(
                    onClick = onDismiss,
                    modifier = Modifier
                ) {
                    Text(
						"Got It",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Comment
                        )
					)
                }
            }
        }
    }
}
