package com.example.myapplication.data

import android.content.Context
import androidx.core.content.edit

object AppPreferences {

    private const val PREFS_NAME = "app_preferences"
    private const val ONBOARDING_COMPLETED = "onboarding_completed"

    fun isOnboardingCompleted(context: Context): Boolean {
        return context
            .getSharedPreferences(
                PREFS_NAME,
                Context.MODE_PRIVATE
            )
            .getBoolean(
                ONBOARDING_COMPLETED,
                false
            )
    }

    fun setOnboardingCompleted(context: Context) {
        context
            .getSharedPreferences(
                PREFS_NAME,
                Context.MODE_PRIVATE
            )
            .edit {
                putBoolean(
                    ONBOARDING_COMPLETED,
                    true
                )
            }
    }
}