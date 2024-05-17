package com.example.parivikshaka

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import com.example.parivikshaka.api.Constant.Companion.PREFERENCE_NAME
import com.example.parivikshaka.utils.LocaleHelper
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)  // night mode
        val mPrefs: SharedPreferences =   getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        LocaleHelper.setLocale(applicationContext,  mPrefs.getString("Locale.Helper.Selected.Language","en-rUS").toString())
    }
}