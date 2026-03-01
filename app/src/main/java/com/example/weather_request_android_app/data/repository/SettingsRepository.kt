package com.example.weather_request_android_app.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingsRepository(private val context: Context) {

	companion object {
		private val IS_DARK_THEME_KEY = booleanPreferencesKey("is_dark_theme")
		private const val DEFAULT_THEME = true
	}

	suspend fun saveTheme(isDark: Boolean) {
		context.dataStore.edit { preferences ->
			preferences[IS_DARK_THEME_KEY] = isDark
		}
	}

	fun getTheme(): Flow<Boolean> {
		return context.dataStore.data.map { preferences ->
			preferences[IS_DARK_THEME_KEY] ?: DEFAULT_THEME
		}
	}
}
