package com.example.pyrkonwenciknew.data.mapper

import com.example.pyrkonwenciknew.data.model.ZoneJson
import com.example.pyrkonwenciknew.domain.model.ZoneDomain

fun ZoneJson.toZoneDomain(): ZoneDomain =
    ZoneDomain(
        name = this.name
    )

fun List<ZoneJson>.toZoneDomainList(): List<ZoneDomain> =
    this.map { it.toZoneDomain() }