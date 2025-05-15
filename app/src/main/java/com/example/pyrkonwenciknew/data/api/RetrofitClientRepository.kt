package com.example.pyrkonwenciknew.data.api

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Dispatcher
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Locale
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RetrofitClientRepository @Inject constructor() {

    companion object {
        private const val CONTENT_TYPE = "Content-Type"
        private const val APPLICATION_JSON = "application/json"
        private const val UTF8 = "utf-8"
        private const val CHARSET = "charset"
        private const val LOCALE = "locale"
        private const val TIMEOUT = 30L
    }

    fun getRetrofitInterface(): Retrofit = Retrofit.Builder()
        .client(getOkHttpClient())
        .baseUrl("https://pyrkon.pl/")
        .addConverterFactory(GsonConverterFactory.create(getGson()))
        .build()

    private fun getOkHttpClient(): OkHttpClient {
        val dispatcher = Dispatcher()
        dispatcher.maxRequests = 1
        return OkHttpClient
            .Builder()
            .dispatcher(dispatcher)
            .addInterceptor(getAuthInterceptor())
            .addInterceptor(getLoginInterceptor())
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    private fun getGson(): Gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
        .create()

    private fun getLoginInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

    private fun getAuthInterceptor(): Interceptor = Interceptor { chain ->
        val original = chain.request()
        val request = original.newBuilder()
            .addHeader(CONTENT_TYPE, APPLICATION_JSON)
            .addHeader(CHARSET, UTF8)
            .addHeader(LOCALE, Locale.getDefault().language)
            .build()

        chain.proceed(request)
    }
}