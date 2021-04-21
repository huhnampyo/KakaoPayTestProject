package com.hnp.kakaopaytestproject.presentation

import com.hnp.kakaopaytestproject.application.AppService
import com.hnp.kakaopaytestproject.application.Callback
import com.hnp.kakaopaytestproject.data.remote.book.BooksResponse
import javax.inject.Inject

class MainRequester @Inject constructor(private val appService: AppService) {
    fun requestSearchBook(query: String,
                         sort: String,
                         page: Int,
                         size: Int,
                         target: String,
                         callback: Callback<BooksResponse>) {
        appService.requestSearchBook(query, sort, page, size, target, callback)
    }
}