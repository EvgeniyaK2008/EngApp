
package com.example.englishapp

import android.content.Context
import android.content.SharedPreferences

object ThemeManager {
    private const val PREFS_NAME = "theme_prefs"
    private const val KEY_IS_DARK_THEME = "is_dark_theme"

    fun isDarkTheme(context: Context): Boolean {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getBoolean(KEY_IS_DARK_THEME, false)
    }

    fun setDarkTheme(context: Context, isDark: Boolean) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putBoolean(KEY_IS_DARK_THEME, isDark).apply()
    }
}
