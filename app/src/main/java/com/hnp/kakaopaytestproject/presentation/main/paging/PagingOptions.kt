package com.hnp.kakaopaytestproject.presentation.main.paging

import androidx.paging.PagedList

object PagingOptions {

    private const val pageSize = 50

    fun pageListConfig(): PagedList.Config {
        return PagedList.Config.Builder()
        .setPageSize(pageSize)
        .setInitialLoadSizeHint(pageSize * 2)
        .setEnablePlaceholders(false)
        .build()
    }
}