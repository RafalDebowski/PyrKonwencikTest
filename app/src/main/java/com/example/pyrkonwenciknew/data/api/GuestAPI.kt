package com.example.pyrkonwenciknew.data.api

import com.example.pyrkonwenciknew.data.model.GuestJson
import retrofit2.Response
import retrofit2.http.GET

interface GuestAPI {
    @GET("/guests.json")
    suspend fun getGuestsList(): Response<List<GuestJson>>
}

