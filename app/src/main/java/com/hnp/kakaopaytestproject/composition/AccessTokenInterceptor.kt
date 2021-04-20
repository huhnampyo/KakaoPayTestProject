package com.hnp.kakaopaytestproject.composition

import com.hnp.kakaopaytestproject.BuildConfig
import com.hnp.kakaopaytestproject.KakaoApp
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AccessTokenInterceptor(private val app: KakaoApp) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest: Request = chain.request().newBuilder()
                        .addHeader("Authorization", "KakaoAK ${BuildConfig.GRADLE_KAKAO_REST_API_KEY}")
                        .build()

        return chain.proceed(newRequest)
    }
}