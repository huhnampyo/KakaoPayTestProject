package com.hnp.kakaopaytestproject.data.remote.book

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Document(
    @Json(name = "authors")
    val authors: List<String>,
    @Json(name = "contents")
    val contents: String,
    @Json(name = "datetime")
    val datetime: String,
    @Json(name = "isbn")
    val isbn: String,
    @Json(name = "price")
    val price: Int,
    @Json(name = "publisher")
    val publisher: String,
    @Json(name = "sale_price")
    val sale_price: Int,
    @Json(name = "status")
    val status: String,
    @Json(name = "thumbnail")
    val thumbnail: String,
    @Json(name = "title")
    val title: String,
    @Json(name = "translators")
    val translators: List<String>,
    @Json(name = "url")
    val url: String,
    var layoutPosition: Int = -1,
    var isLike: Boolean = false
)