package com.hnp.kakaopaytestproject.data

import com.hnp.kakaopaytestproject.application.AppService
import com.hnp.kakaopaytestproject.application.Callback
import com.hnp.kakaopaytestproject.data.remote.RemoteService
import com.hnp.kakaopaytestproject.data.remote.book.BooksResponse
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class AppServiceImpl @Inject constructor(
    val remoteApi: RemoteService
) : AppService {

    override fun requestSearchBook(
        query: String,
        sort: String,
        page: Int,
        size: Int,
        target: String,
        callback: Callback<BooksResponse>
    ) {
        remoteApi.getSearchBook(query=query,
            sort=sort,
            page=page,
            size=size,
            target=target)
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = { response ->
//                    callback.onSuccess(
//                    )
                },
                onError = {
                    it.printStackTrace()
                }
            )
    }
}