package com.hnp.kakaopaytestproject.data

import com.hnp.kakaopaytestproject.application.AppService
import com.hnp.kakaopaytestproject.application.Callback
import com.hnp.kakaopaytestproject.data.remote.RemoteService
import com.hnp.kakaopaytestproject.data.remote.book.BooksResponse
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import javax.inject.Inject

class AppServiceImpl @Inject constructor(
    private val remoteApi: RemoteService,
    private val subject: PublishSubject<String>
) : AppService {

    override fun requestSearchBook(
        query: String,
        page: Int,
        size: Int,
        callback: Callback<BooksResponse>
    ) {
        remoteApi.getSearchBook(
            query=query,
            page=page,
            size=size
        ).subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = { response ->
                    subject.onNext("이벤트 에러 전달")
                    callback.onSuccess(response)
                },
                onError = {
                    it.printStackTrace()
                }
            )
    }

    override fun requestSearchBook(query: String, page: Int, size: Int): Single<BooksResponse> {
        return remoteApi.getSearchBook(
            query=query,
            page=page,
            size=size
        )
    }
}