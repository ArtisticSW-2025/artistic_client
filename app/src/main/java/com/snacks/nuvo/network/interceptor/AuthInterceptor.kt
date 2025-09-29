package com.snacks.nuvo.network.interceptor

import com.snacks.nuvo.TempAuthManager
import okhttp3.Interceptor
import okhttp3.Response

// 헤더에 accessToken을 삽입하는 인터셉터
class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val url = originalRequest.url.toString()

        // 로그인/회원가입 시 적용X
        if (url.contains("/login") || url.contains("/register")) {
            return chain.proceed(originalRequest)
        }

        val token = TempAuthManager.getAccessToken()

        val newRequest = if (token != null) {
            originalRequest.newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        } else {
            originalRequest
        }

        return chain.proceed(newRequest)
    }

}