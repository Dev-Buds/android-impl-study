package com.example.data.util

import com.example.domain.model.common.NetworkException
import retrofit2.Response

suspend inline fun <T> safeApiCall(crossinline apiCall: suspend () -> Response<T>): Result<T> =
    runCatching {
        apiCall()
    }.mapCatching { response ->
        if (!response.isSuccessful) throw httpErrorToException(response)
        requireNotNull(response.body()) { "응답 본문이 비어 있습니다." }
    }.recoverCatching { throwable ->
        throw mapNetworkException(throwable)
    }

fun <T> httpErrorToException(response: Response<T>): Throwable {
    val code = response.code()
    val errorBody = response.errorBody()?.string()
    val message =
        buildString {
            append("요청이 실패했습니다. (code=$code)")
            if (!errorBody.isNullOrBlank()) append(" / errorBody=$errorBody")
        }
    return NetworkException.Http(code, message)
}

fun mapNetworkException(throwable: Throwable): Throwable =
    when (throwable) {
        is java.net.UnknownHostException -> NetworkException.NoInternet
        is java.net.SocketTimeoutException -> NetworkException.Timeout
        is NetworkException -> throwable
        else -> NetworkException.Unknown(throwable.message)
    }
