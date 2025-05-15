package com.example.pyrkonwenciknew.domain.repository

import com.example.pyrkonwenciknew.domain.BaseResponse
import com.example.pyrkonwenciknew.domain.model.GuestDomain

interface GuestRepository {
    suspend fun getGuestsList(): BaseResponse<List<GuestDomain>>
}