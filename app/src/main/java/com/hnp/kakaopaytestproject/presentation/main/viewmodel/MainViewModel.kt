package com.hnp.kakaopaytestproject.presentation.main.viewmodel

import androidx.lifecycle.ViewModel
import com.hnp.kakaopaytestproject.Test
import com.hnp.kakaopaytestproject.application.Callback
import com.hnp.kakaopaytestproject.data.remote.book.BooksResponse
import com.hnp.kakaopaytestproject.presentation.MainRequester
import com.hnp.kakaopaytestproject.presentation.viewmodel.LiveVar
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainViewModel @Inject constructor(
    val mainRequester: MainRequester
): ViewModel() {

    val openLayoutType = LiveVar<String>()

    fun requestSearchBook(bookName: String) {
        mainRequester.requestSearchBook(query=bookName,
            sort="",
            page=1,
            size=1,
            target="",
            callback = object: Callback<BooksResponse> {
                override fun onSuccess(result: BooksResponse) {

                }

                override fun onFailure(e: Throwable) {
                    e.printStackTrace()
                }
            }
        )
    }
}