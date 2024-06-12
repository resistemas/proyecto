package com.example.proyecto.Model.Header

import com.example.proyecto.Core.Variables
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer ${Variables.API_KEY}")

            .build()

//            .addHeader("Accept", "application/json")



        return chain.proceed(request)
    }
}