package com.example.pixelraffle.network.repository

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitHelper {

    private val retrofit: Retrofit
    init{
        val builder = Retrofit.Builder()
            .baseUrl("https://private-06d7d0-instatask.apiary-mock.com/")
            .addConverterFactory(GsonConverterFactory.create()) // create object conver to json/gson
            .addCallAdapterFactory(CoroutineCallAdapterFactory()) //get data connect with the class

        val loggingInterceptor = HttpLoggingInterceptor() // Optional Call
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .writeTimeout(0, TimeUnit.MICROSECONDS)
            .writeTimeout(2, TimeUnit.MINUTES) // 2 minutes, or time out
            .writeTimeout(1, TimeUnit.MINUTES).build() // 1 minutes, server time out

        retrofit = builder.client(okHttpClient).build()
    }

    fun getAuthService():AuthAPIService
    {
        return retrofit.create(AuthAPIService::class.java) //  class<AuthAPIService> this is equivalent to AuthAPIService::class.java

    }
}