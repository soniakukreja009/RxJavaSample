package com.example.rxjavasample

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

class AppSession(private val context: Context) {

    companion object {
        private const val DATASTORE_KEY = "user"
        private val LANG = stringPreferencesKey("lang")
        private val Context.userPreferencesDataStore: DataStore<Preferences> by preferencesDataStore(
            name = DATASTORE_KEY
        )
    }

    val getLanguage = context.userPreferencesDataStore.data
        .map { preferences ->
            preferences[LANG]?: "en"
        }

    suspend fun saveLanguage(lang: String) {
        context.userPreferencesDataStore.edit { preferences ->
            preferences[LANG] = lang
        }
    }

    suspend fun clearSession() {
        context.userPreferencesDataStore.edit { preferences ->
            preferences.clear()
        }
    }
}