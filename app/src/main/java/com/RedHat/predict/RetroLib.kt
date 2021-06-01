package com.RedHat.predict

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroLib {
//    fun create(): QueryApi{
//        val loggingInterceptor =
//            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
//
//        val client = OkHttpClient.Builder()
//            .addInterceptor(loggingInterceptor)
//            .build()
//
//        val retrofit = Retrofit.Builder()
//            .addConverterFactory(GsonConverterFactory.create())
//            .baseUrl("https://api.themoviedb.org/")
//            .client(client)
//            .build()
//        return retrofit.create(QueryApi::class.java)
//    }
}