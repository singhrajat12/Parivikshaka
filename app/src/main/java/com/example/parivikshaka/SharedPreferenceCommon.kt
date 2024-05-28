package com.example.parivikshaka

import android.content.Context
import android.content.SharedPreferences
import com.example.parivikshaka.api.Constant.Companion.PREFERENCE_NAME

import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class  SharedPreferenceCommon @Inject constructor(@ApplicationContext mContext: Context) {


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

    fun gettoken():String{
        return mPrefs.getString("token","").toString()
    }



//    fun setUserData(userInfo:String){
//        mPrefs.edit().putString("userInfo",userInfo).apply()
//    }

    }



