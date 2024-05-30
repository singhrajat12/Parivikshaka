package com.example.parivikshaka

import android.content.Context
import android.content.SharedPreferences
import com.example.parivikshaka.api.Constant.Companion.PREFERENCE_NAME
import com.example.parivikshaka.models.VerifyOtpResponse
import com.google.gson.Gson

import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class  SharedPreferenceCommon @Inject constructor(@ApplicationContext mContext: Context) {
    private val gson = Gson()

    private var mPrefs: SharedPreferences =
        mContext.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

    fun getOneTimeInstall():Boolean{
        return mPrefs.getBoolean("oneTimeInstall",false)
    }
    fun setToken(value: String) {
        mPrefs.edit().putString("token", value).apply()
    }


    fun clearSharePreference():Boolean{
        return mPrefs.edit().clear().commit()
    }
    fun setauthdata(value: String) {
        mPrefs.edit().putString("authdata", value).apply()
    }
    fun getauthdata():String{
        return mPrefs.getString("authdata","").toString()
    }

    fun gettoken():String{
        return mPrefs.getString("token","").toString()
    }


}




