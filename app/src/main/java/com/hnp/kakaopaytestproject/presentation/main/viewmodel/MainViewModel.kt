package com.hnp.kakaopaytestproject.presentation.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.*
import com.hnp.kakaopaytestproject.data.remote.book.Document
import com.hnp.kakaopaytestproject.presentation.MainRequester
import com.hnp.kakaopaytestproject.presentation.main.paging.BooksDataSource
import com.hnp.kakaopaytestproject.presentation.main.paging.PagingOptions
import com.hnp.kakaopaytestproject.presentation.viewmodel.LiveVar
import io.reactivex.rxjava3.subjects.PublishSubject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainViewModel @Inject constructor(
    val mainRequester: MainRequester
): ViewModel() {

    val errorMessage = LiveVar<String>()

    val selectedBook = LiveVar<Document>()
    val isLikeChanged = LiveVar(false)

    init {
        actionInit()
    }

    private fun actionInit(){
        errorMessage.set(null)
        selectedBook.set(null)
        isLikeChanged.set(false)
    }

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

    fun clear(){
        actionInit()
    }
}
