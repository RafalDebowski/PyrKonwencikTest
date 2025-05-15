package com.example.pyrkonwenciknew.data.mapper

import com.example.pyrkonwenciknew.data.model.GuestJson
import com.example.pyrkonwenciknew.domain.model.GuestDomain

fun GuestJson.toGuestDomain(): GuestDomain =
    GuestDomain(
        imageUrl = this.imageUrl,
        name = this.name,
        summary = this.summary,
        zones = this.zones
    )

fun List<GuestJson>.toGuestDomainList(): List<GuestDomain> =
    this.map { it.toGuestDomain() }