package com.example.pyrkonwenciknew.domain.model

data class GuestDomain(
    val imageUrl: String,
    val name: String,
    val summary: String,
    val zones: List<String>
)