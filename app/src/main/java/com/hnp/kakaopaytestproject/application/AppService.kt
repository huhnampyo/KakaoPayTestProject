package com.hnp.kakaopaytestproject.application

import com.hnp.kakaopaytestproject.data.remote.book.BooksResponse
import io.reactivex.rxjava3.core.Single


interface AppService {

    fun requestSearchBook(
        query: String, //검색을 원하는 질의어
        page: Int, //결과 페이지 번호, 1~50 사이의 값, 기본 값 1
        size: Int, //한 페이지에 보여질 문서 수, 1~50 사이의 값, 기본 값 10
        callback: Callback<BooksResponse>
    )

    fun requestSearchBook(
        query: String, //검색을 원하는 질의어
        page: Int, //결과 페이지 번호, 1~50 사이의 값, 기본 값 1
        size: Int //한 페이지에 보여질 문서 수, 1~50 사이의 값, 기본 값 10
    ): Single<BooksResponse>
}