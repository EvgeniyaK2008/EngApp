// OnboardingManager.kt
package com.example.englishapp

import android.content.Context
import android.content.SharedPreferences

object OnboardingManager {
    private const val PREFS_NAME = "onboarding_prefs"
    private const val KEY_ONBOARDING_STEP = "onboarding_step"
    private const val KEY_ONBOARDING_COMPLETED = "onboarding_completed"

    fun getOnboardingStep(context: Context): Int {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getInt(KEY_ONBOARDING_STEP, 1)
    }

    fun setOnboardingStep(context: Context, step: Int) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putInt(KEY_ONBOARDING_STEP, step).apply()
    }

    fun isOnboardingCompleted(context: Context): Boolean {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getBoolean(KEY_ONBOARDING_COMPLETED, false)
    }

    fun setOnboardingCompleted(context: Context, completed: Boolean) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putBoolean(KEY_ONBOARDING_COMPLETED, completed).apply()
    }

    fun resetOnboarding(context: Context) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().clear().apply()
    }
}