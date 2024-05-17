package com.example.parivikshaka.db

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.parivikshaka.MainActivity
import com.example.parivikshaka.SharedPreferenceCommon
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class OAuthInterceptor @Inject constructor(
    private val sharedprefernce: SharedPreferenceCommon, @ApplicationContext mContext: Context
) : Interceptor {
    val context = mContext
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        request =
            request
                .newBuilder()
                .addHeader("Content-Type", "application/json")
                .build()
        val response = chain.proceed(request)
        if (response.code == 401) {
            Handler(Looper.getMainLooper()).post {
                redirectToLoginScreen(context = context)
            }

        }
        return response
    }

    private fun redirectToLoginScreen(context: Context) {
        sharedprefernce.clearSharePreference()
        val loginIntent = Intent(context, MainActivity::class.java)
        loginIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(context, loginIntent, null)
    }
}


