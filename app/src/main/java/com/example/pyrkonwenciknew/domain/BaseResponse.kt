package com.example.pyrkonwenciknew.domain

import com.google.gson.Gson
import java.net.SocketException

class BaseResponse<T>(
    val data: T? = null,
    val errorMessage: String? = null,
    val code: Int? = null,
) {

    fun isError(): Boolean = isSuccessful().not()
    fun isSuccessful(): Boolean =
        this.data != null && errorMessage == null && code != null && code >= 200 && code < 300

    companion object {
        fun <T> error(errorMessage: String, code: Int?): BaseResponse<T> {
            return try {
                BaseResponse(
                    errorMessage = Gson().fromJson(
                        errorMessage,
                        ErrorDto::class.java
                    )?.message.orEmpty(),
                    code = code
                )
            } catch (socketException: SocketException) {
                BaseResponse(errorMessage = errorMessage, code = 12345)
            } catch (exception: Exception) {
                BaseResponse(errorMessage = errorMessage, code = code)
            }
        }

        fun <T> success(data: T, code: Int?): BaseResponse<T> =
            BaseResponse(data = data, code = code)
    }
}

data class ErrorDto(val message: String)