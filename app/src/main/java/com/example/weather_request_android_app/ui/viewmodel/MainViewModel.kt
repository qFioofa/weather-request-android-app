package com.example.weather_request_android_app.ui.viewmodel

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather_request_android_app.data.repository.SettingsRepository
import com.example.weather_request_android_app.data.repository.WeatherUrlGenerator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(context: Context) : ViewModel() {
    private val settingsRepo = SettingsRepository(context)

    private val _isDarkTheme = MutableStateFlow(true)
    val isDarkTheme: StateFlow<Boolean> = _isDarkTheme

    private val _selectedCity = MutableStateFlow("Moscow")
    val selectedCity: StateFlow<String> = _selectedCity

    private val _selectedDays = MutableStateFlow(1)
    val selectedDays: StateFlow<Int> = _selectedDays

    init {
        loadTheme()
    }

    private fun loadTheme() {
        viewModelScope.launch {
            settingsRepo.getTheme().collect { isDark ->
                _isDarkTheme.value = isDark
            }
        }
    }

    fun toggleTheme() {
        viewModelScope.launch {
            val new = !_isDarkTheme.value
            settingsRepo.saveTheme(new)
            _isDarkTheme.value = new
        }
    }

    fun updateCity(city: String) {
        _selectedCity.value = city
    }

    fun updateDays(days: Int) {
        _selectedDays.value = days
    }

    fun openWeatherInBrowser(context: Context) {
        val url = WeatherUrlGenerator.generateUrl(_selectedCity.value, _selectedDays.value)
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        }
    }
}
