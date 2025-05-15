package com.example.pyrkonwenciknew.domain.usecase

import com.example.pyrkonwenciknew.domain.BaseResponse
import com.example.pyrkonwenciknew.domain.model.GuestDomain
import com.example.pyrkonwenciknew.domain.repository.GuestRepository

class GuestListUseCase(
    private val guestRepository: GuestRepository
) {
    suspend fun getGuestsList(): BaseResponse<List<GuestDomain>> =
        guestRepository.getGuestsList()
}