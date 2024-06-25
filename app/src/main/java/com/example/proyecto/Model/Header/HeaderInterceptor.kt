package com.example.proyecto.Model.Header

import android.util.Log
import com.example.proyecto.Core.Variables
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        Log.d("IRAMUS TOKEN", "Authorization Bearer ${Variables.API_KEY}")
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer ${Variables.API_KEY}")
            .build()

        return chain.proceed(request)
    }
}