package com.hnp.kakaopaytestproject.application

import com.hnp.kakaopaytestproject.data.remote.book.BooksResponse


interface AppService {

    fun requestSearchBook(
        query: String, //검색을 원하는 질의어
        sort: String, //결과 문서 정렬 방식, accuracy(정확도순) 또는 latest(발간일순)
        page: Int, //결과 페이지 번호, 1~50 사이의 값, 기본 값 1
        size: Int, //한 페이지에 보여질 문서 수, 1~50 사이의 값, 기본 값 10
        target: String, //검색 필드 제한
        callback: Callback<BooksResponse>
    )
}