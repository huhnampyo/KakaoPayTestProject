package com.hnp.kakaopaytestproject.presentation.main.paging

import androidx.paging.PagedList

object PagingOptions {

    private const val PAGE_SIZE = 50

    fun pageListConfig(): PagedList.Config {
        return PagedList.Config.Builder()
        .setPageSize(PAGE_SIZE)
        .setInitialLoadSizeHint(PAGE_SIZE)
        .setEnablePlaceholders(false)
        .setPrefetchDistance(PAGE_SIZE/2)
        .build()
    }
}