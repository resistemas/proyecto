package com.example.proyecto.Network

import com.example.proyecto.Core.Variables
import com.example.proyecto.Model.Header.HeaderInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

object RetrofitCliente {
    val webService : WebService by lazy {
        Retrofit.Builder().baseUrl(Variables.API_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(getCliente())
            .build().create(WebService::class.java)
    }

    private fun getCliente() : OkHttpClient{
        return OkHttpClient.Builder().addInterceptor(HeaderInterceptor()).build()
    }
}