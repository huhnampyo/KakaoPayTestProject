package com.hnp.kakaopaytestproject.presentation.main.paging

import android.util.Log
import androidx.paging.PositionalDataSource
import com.hnp.kakaopaytestproject.application.Callback
import com.hnp.kakaopaytestproject.data.remote.book.BooksResponse
import com.hnp.kakaopaytestproject.data.remote.book.Document
import com.hnp.kakaopaytestproject.presentation.MainRequester

class BooksDataSource(
    private val searchName: String,
    private val mainRequester: MainRequester,
    private val errorAction: (Throwable) -> Unit = {}
) : PositionalDataSource<Document>() {

    private val totalElements by lazy { totalElements() }
    private var page = 1
    private var isPagingEnd = false

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Document>) {
        val firstLoadPosition = computeInitialLoadPosition(params, totalElements)
        val loadSize = computeInitialLoadSize(params, firstLoadPosition, totalElements)

        mainRequester.requestSearchBook(query = searchName, page=page++, size=loadSize, callback=object :
            Callback<BooksResponse>{
            override fun onSuccess(result: BooksResponse) {
                isPagingEnd = result.meta.is_end
                callback.onResult(result.documents, 0)
            }

            override fun onFailure(e: Throwable) {
                errorAction.invoke(e)
            }
        })
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Document>) {

        Log.d("hnp", "page : $page")
        Log.d("hnp", "params.startPosition, : $${params.startPosition}")
        Log.d("hnp", "totalElements : $totalElements")

        if( !isPagingEnd ){

            mainRequester.requestSearchBook(query = searchName, page=page++, size=params.loadSize, callback=object :
                    Callback<BooksResponse>{
                override fun onSuccess(result: BooksResponse) {
                    isPagingEnd = result.meta.is_end
                    callback.onResult(result.documents)
                }

                override fun onFailure(e: Throwable) {
                    errorAction.invoke(e)
                }
            })
        }

    }

    private fun totalElements(): Int {
        return try {
            mainRequester.requestSearchBook(searchName, 1, 1).blockingGet().meta.total_count
        } catch (e: Exception) {
            0
        }
    }
}