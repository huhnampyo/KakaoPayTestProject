package com.hnp.kakaopaytestproject.presentation.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.*
import com.hnp.kakaopaytestproject.data.remote.book.Document
import com.hnp.kakaopaytestproject.presentation.MainRequester
import com.hnp.kakaopaytestproject.presentation.main.paging.BooksDataSource
import com.hnp.kakaopaytestproject.presentation.main.paging.PagingOptions
import com.hnp.kakaopaytestproject.presentation.viewmodel.LiveVar
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainViewModel @Inject constructor(
    val mainRequester: MainRequester
): ViewModel() {

    val errorMessage = LiveVar<String>()

    val selectedBook = LiveVar<Document>()

    fun requestSearchBook(bookName: String): LiveData<PagedList<Document>> {
        return LivePagedListBuilder<Int, Document>(dataSource(bookName), PagingOptions.pageListConfig()).build()
    }

    private fun dataSource(bookName : String): DataSource.Factory<Int, Document> {
        return object : DataSource.Factory<Int, Document>() {
            override fun create(): DataSource<Int, Document> {
                return BooksDataSource(bookName, mainRequester)
            }
        }
    }
}
