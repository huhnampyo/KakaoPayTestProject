package com.hnp.kakaopaytestproject.application

interface Callback<T> {
    fun onSuccess(result: T)

    fun onFailure(e: Throwable)
}