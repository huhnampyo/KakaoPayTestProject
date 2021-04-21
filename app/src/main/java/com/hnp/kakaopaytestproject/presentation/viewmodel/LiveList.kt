package com.hnp.kakaopaytestproject.presentation.viewmodel

class LiveList<R>(list: List<R>? = null) : LiveVar<List<R>>(list) {
    fun replaceAll(list: List<R>) {
        dataSource = list
        _mutableLiveData.postValue(dataSource)
    }

    fun updateItem(updater: (oldItem: R) -> R) {
        replaceAll(
            dataSource?.map { oldItem ->
                updater(oldItem)
            } ?: emptyList()
        )
    }
}