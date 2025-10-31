package com.example.domain.model.common

sealed class NetworkException(
    message: String?,
) : Exception(message) {
    data object NoInternet : NetworkException("인터넷 연결을 확인해주세요.") {
        private fun readResolve(): Any = NoInternet
    }

    data object Timeout : NetworkException("요청 시간이 초과되었습니다.") {
        private fun readResolve(): Any = Timeout
    }

    data class Http(
        val code: Int,
        val detail: String?,
    ) : NetworkException(detail ?: "HTTP 오류가 발생했습니다. (code=$code)")

    data class Unknown(
        val reason: String?,
    ) : NetworkException(reason ?: "알 수 없는 오류가 발생했습니다.")
}
