package com.hnp.kakaopaytestproject.presentation.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.*
import com.hnp.kakaopaytestproject.data.remote.book.Document
import com.hnp.kakaopaytestproject.presentation.MainRequester
import com.hnp.kakaopaytestproject.presentation.main.paging.BooksDataSource
import com.hnp.kakaopaytestproject.presentation.main.paging.PagingOptions
import com.hnp.kakaopaytestproject.presentation.viewmodel.LiveVar
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainViewModel @Inject constructor(
    val mainRequester: MainRequester
): ViewModel() {

    val errorMsg = LiveVar<String>()
    val selectedBook = LiveVar<Document>()
    val isLikeChanged = LiveVar(false)

    private fun actionInit(){
        selectedBook.set(null)
        isLikeChanged.set(false)
    }

    fun requestSearchBook(bookName: String): LiveData<PagedList<Document>> {
        errorMsg.set(null)
        return LivePagedListBuilder<Int, Document>(dataSource(bookName), PagingOptions.pageListConfig()).build()
    }

    private fun dataSource(bookName : String): DataSource.Factory<Int, Document> {
        return object : DataSource.Factory<Int, Document>() {
            override fun create(): DataSource<Int, Document> {
                return BooksDataSource(bookName, mainRequester) {
                    var error = "통신에러가 발생했습니다\n다시 시도해주세요"
                    if(it is HttpException){
                        error = "${it.code()} : ${it.message}"

                    }
                    errorMsg.set(error)
                }
            }
        }
    }

    fun clear(){
        actionInit()
    }
}
