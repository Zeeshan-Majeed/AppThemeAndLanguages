package com.brain.themes.applanguages.helper.brainthemesandapplanguages.utils

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.LocaleManagerCompat
import androidx.core.os.LocaleListCompat
import com.brain.themes.applanguages.helper.brainthemesandapplanguages.models.Languages

object LanguageUtils {

    fun Context.getSystemDefaultLanguage() =
        LocaleManagerCompat.getSystemLocales(this).toLanguageTags().substringBefore(",")

    fun userSelectedAppLanguage() = AppCompatDelegate.getApplicationLocales()

    fun setUserSelectedLanguageForApp(language: Languages) {
        AppCompatDelegate.setApplicationLocales(
            LocaleListCompat.forLanguageTags(language.languageCode)
        )

    }
}