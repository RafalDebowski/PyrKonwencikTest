package com.example.pyrkonwenciknew.data.api

import com.example.pyrkonwenciknew.domain.BaseResponse
import retrofit2.Response


suspend fun <T> requestHandler(request: suspend () -> Response<T>?): BaseResponse<T> {
    return try {
        request()?.handle() ?: run {
            BaseResponse.error("Request handle Error", null)
        }
    } catch (exception: Exception) {
        BaseResponse.error(exception.localizedMessage.orEmpty(), null)
    }
}

fun <T> Response<T>.handle(): BaseResponse<T> {

    return when {
        this.isSuccessful -> {
            this.body()?.let {
                BaseResponse.success(data = it, code = code())
            } ?: run {
                BaseResponse.error(errorMessage = "Empty body", code())
            }
        }
        this.errorBody() != null -> {
            BaseResponse.error(errorMessage = errorBody()?.string() ?: "Unknown error body", code = code())
        }
        else -> {
            BaseResponse(errorMessage = "Unknown error", code = code())
        }
    }

}