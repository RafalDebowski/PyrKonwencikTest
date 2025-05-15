package com.example.pyrkonwenciknew.data.repository

import com.example.pyrkonwenciknew.data.api.GuestAPI

import com.example.pyrkonwenciknew.data.api.requestHandler
import com.example.pyrkonwenciknew.data.mapper.toGuestDomainList
import com.example.pyrkonwenciknew.domain.BaseResponse
import com.example.pyrkonwenciknew.domain.model.GuestDomain
import com.example.pyrkonwenciknew.domain.repository.GuestRepository
import javax.inject.Inject

class GuestRepositoryImpl @Inject constructor(
    private val guestApi: GuestAPI
) : GuestRepository {

    override suspend fun getGuestsList(): BaseResponse<List<GuestDomain>> {
        val response = requestHandler {
            guestApi.getGuestsList()
        }

        return when {
            response.isSuccessful() && response.data != null -> {
                BaseResponse.success(
                    data = response.data.toGuestDomainList(),
                    code = response.code
                )
            }

            else -> {
                BaseResponse.error(
                    errorMessage = response.errorMessage.orEmpty(),
                    code = response.code
                )
            }
        }
    }
}