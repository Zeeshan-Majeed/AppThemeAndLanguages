package com.brain.themes.applanguages.helper.brainthemesandapplanguages

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.brain.themes.applanguages.helper.brainthemesandapplanguages.utils.MyPreference

class App : Application() {
    lateinit var myPref: MyPreference

    override fun onCreate() {
        super.onCreate()
        myPref = MyPreference(applicationContext)
        when (myPref.getAppTheme()) {
            0 -> {
                // system default
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }

            1 -> {
                // Light
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }

            2 -> {
                // Dark
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }
    }
}