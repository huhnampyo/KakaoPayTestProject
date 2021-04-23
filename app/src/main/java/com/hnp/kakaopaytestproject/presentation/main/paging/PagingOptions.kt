package com.hnp.kakaopaytestproject.presentation.main.paging

import androidx.paging.PagedList

object PagingOptions {

    private const val pageSize = 50

    fun pageListConfig(): PagedList.Config {
        return PagedList.Config.Builder()
        .setPageSize(pageSize)
        .setInitialLoadSizeHint(pageSize)
        .setEnablePlaceholders(false)
        .setPrefetchDistance(pageSize/2)
        .build()
    }
}