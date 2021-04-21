package com.hnp.kakaopaytestproject.data.remote.book

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Meta(
    @Json(name = "is_end")
    val is_end: Boolean,
    @Json(name = "pageable_count")
    val pageable_count: Int,
    @Json(name = "total_count")
    val total_count: Int
)