package com.example.weather_request_android_app.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather_request_android_app.data.repository.SettingsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainViewModel(context: Context) : ViewModel() {
	private val settingsRepo = SettingsRepository(context)

	private val _isDarkTheme = MutableStateFlow(true)
	val isDarkTheme: StateFlow<Boolean> = _isDarkTheme

	init {
		loadTheme()
	}

	private fun loadTheme() {
		viewModelScope.launch {
			settingsRepo.getTheme().collectLatest { isDark ->
				_isDarkTheme.value = isDark
			}
		}
	}

	fun toggleTheme() {
		viewModelScope.launch {
			val newValue = !_isDarkTheme.value
			settingsRepo.saveTheme(newValue)
			_isDarkTheme.value = newValue
		}
	}
}
