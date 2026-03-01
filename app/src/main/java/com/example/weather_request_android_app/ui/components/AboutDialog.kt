package com.example.weather_request_android_app.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun AboutDialog(onDismiss: () -> Unit) {
	Dialog(
		onDismissRequest = onDismiss,
		properties = DialogProperties(usePlatformDefaultWidth = false)
	) {
		Card(
			modifier = Modifier
				.fillMaxWidth(0.8f)
				.padding(16.dp)
		) {
			Column(
				modifier = Modifier.padding(16.dp),
				verticalArrangement = Arrangement.spacedBy(8.dp)
			) {
				Text(
					text = "About Weather App",
					style = MaterialTheme.typography.headlineSmall
				)
				Text(
					text = "Version: 1.0\nAuthor: Me",
					style = MaterialTheme.typography.bodyMedium
				)
				Spacer(modifier = Modifier.height(16.dp))
				Button(
					onClick = onDismiss,
					modifier = Modifier.align(Alignment.End)
				) {
					Text("OK")
				}
			}
		}
	}
}
