package com.example.odyssey_day2

import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object API3 {
    val apiInterface: Api3_Interface by lazy {  //需要使用到時，只會初始化一次

        val logging = HttpLoggingInterceptor()
        logging.level = (HttpLoggingInterceptor.Level.BODY)

        val myOkHttpClient = OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.SECONDS)
//            .addInterceptor(object : Interceptor {
//                override fun intercept(chain: Interceptor.Chain): Response {
//                    val original = chain.request()
//
//                    val requestBuilder = original.newBuilder()
//                        .header("Content-Type", "application/json")
//                        .header("Accept", "application/json")
//
//                    return chain.proceed(requestBuilder.build())
//                }
//            })
            .addInterceptor(logging)
            .build()

        //retrofit實體
        return@lazy Retrofit.Builder()
            .baseUrl("https://d986577a.ngrok.io")
            .client(myOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api3_Interface::class.java)
    }
}