package com.example.pyrkonwenciknew.data.api

import android.content.Context
import com.example.pyrkonwenciknew.data.model.GuestJson
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody
import retrofit2.Response

class MockGuestAPI (private val context: Context) : GuestAPI {

    override suspend fun getGuestsList(): Response<List<GuestJson>> {
        return try {
            val jsonString = context.assets.open("guests.json")
                .bufferedReader().use { it.readText() }

            val type = object : TypeToken<List<GuestJson>>() {}.type
            val data: List<GuestJson> = Gson().fromJson(jsonString, type)

            Response.success(data)
        } catch (e: Exception) {
            val errorMessage = e.message ?: "Unknown error occurred"
            val errorBody = ResponseBody.create(
                "application/json".toMediaType(),
                """{"error": "$errorMessage"}"""
            )
            Response.error(404, errorBody)
        }
    }
}