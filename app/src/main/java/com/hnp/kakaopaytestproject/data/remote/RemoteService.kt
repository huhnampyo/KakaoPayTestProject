package com.hnp.kakaopaytestproject.data.remote

import com.hnp.kakaopaytestproject.data.remote.book.BooksResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface RemoteService {

    @GET("v3/search/book")
    fun getSearchBook(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Single<BooksResponse>

}