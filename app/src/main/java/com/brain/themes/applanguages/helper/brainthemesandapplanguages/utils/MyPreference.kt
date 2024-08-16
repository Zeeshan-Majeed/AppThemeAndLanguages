package com.brain.themes.applanguages.helper.brainthemesandapplanguages.utils

import android.content.Context
import android.content.SharedPreferences

const val PREF_NAME = "my_pref_name"
const val APP_THEME = "app_theme"
const val APP_LANGUAGE_NAME = "app_language_name"
const val APP_LANGUAGE_CODE = "app_language_name"

class MyPreference(context: Context) {

    private var sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun setAppTheme(value: Int) {
        sharedPreferences.edit().putInt(APP_THEME, value).apply()
    }

    fun getAppTheme(): Int {
        return sharedPreferences.getInt(APP_THEME, 0)
    }

    fun setAppLanguageName(value: String) {
        sharedPreferences.edit().putString(APP_LANGUAGE_NAME, value).apply()
    }

    fun getAppLanguagesName(): String {
        return sharedPreferences.getString(APP_LANGUAGE_NAME, "English") ?: "English"
    }
    fun setAppLanguageCode(value: String) {
        sharedPreferences.edit().putString(APP_LANGUAGE_CODE, value).apply()
    }

    fun getAppLanguagesCode(): String {
        return sharedPreferences.getString(APP_LANGUAGE_CODE, "en") ?: "en"
    }
}