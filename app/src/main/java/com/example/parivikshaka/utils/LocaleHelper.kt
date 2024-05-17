package com.example.parivikshaka.utils

import android.annotation.TargetApi
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import com.example.parivikshaka.api.Constant
import java.util.*


object LocaleHelper {
    const val SELECTED_LANGUAGE = "Locale.Helper.Selected.Language"

        // the method is used to set the language at runtime
        fun setLocale(context: Context, language: String) {
        persist(context, language)

        // updating the language for devices above android nougat
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            updateResources(context, language)
        } else updateResourcesLegacy(context, language)
        // for devices having lower version of android os
    }

    private fun persist(context: Context, language: String) {

         var mPrefs: SharedPreferences =    context.getSharedPreferences(Constant.PREFERENCE_NAME, Context.MODE_PRIVATE)
        val editor = mPrefs.edit()
        editor.putString(SELECTED_LANGUAGE, language)
        editor.apply()
    }



    // the method is used update the language of application by creating
    // object of inbuilt Locale class and passing language argument to it
    @TargetApi(Build.VERSION_CODES.N)
    private fun updateResources(context: Context, language: String) {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val resources: Resources = context.resources
        val config: Configuration = resources.configuration
        config.setLocale(locale)
        context.resources.updateConfiguration(config, context.resources.displayMetrics);

    }

    private fun updateResourcesLegacy(context: Context, language: String) {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val resources = context.resources
        val configuration = resources.configuration
        configuration.locale = locale
        configuration.setLayoutDirection(locale)
        resources.updateConfiguration(configuration, resources.displayMetrics)

    }
}