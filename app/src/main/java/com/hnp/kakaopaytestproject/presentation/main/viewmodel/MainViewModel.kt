package com.hnp.kakaopaytestproject.presentation.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.google.gson.Gson
import com.hnp.kakaopaytestproject.data.remote.ErrorResponse
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
        errorMsg.set(null)
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
                        val errorBodyJson = it.response()?.errorBody()?.string()
                        errorBodyJson?.let {errorString ->
                            var errorResponse = Gson().fromJson(errorString, ErrorResponse::class.java)
                            if(!errorResponse.message.isNullOrEmpty()){
                                error = errorResponse.message
                            }
                        }
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
