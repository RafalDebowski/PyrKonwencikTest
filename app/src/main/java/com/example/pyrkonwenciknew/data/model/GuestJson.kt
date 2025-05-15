package com.example.pyrkonwenciknew.data.model

import com.google.gson.annotations.SerializedName

data class GuestJson(
    @SerializedName("imageURL") val imageUrl: String,
    @SerializedName("name") val name: String,
    @SerializedName("summary") val summary: String,
    @SerializedName("zones") val zones: List<String>
)