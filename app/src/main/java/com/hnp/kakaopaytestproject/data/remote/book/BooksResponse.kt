package com.hnp.kakaopaytestproject.data.remote.book

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BooksResponse(
    @Json(name = "documents")
    val documents: List<Document>,
    @Json(name = "meta")
    val meta: Meta
)


