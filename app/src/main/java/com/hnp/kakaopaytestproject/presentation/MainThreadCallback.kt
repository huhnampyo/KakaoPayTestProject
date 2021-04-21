package com.hnp.kakaopaytestproject.presentation

import com.hnp.kakaopaytestproject.application.Callback

abstract class MainThreadCallback<T> : Callback<T> {
    override fun onSuccess(result: T) {
        runOnUiThread {
            handleResultOnMain(it)
        }
    }

    abstract fun handleResultOnMain(result: T)

    private fun runOnUiThread(successCallback: (T) -> Unit) {

    }
}