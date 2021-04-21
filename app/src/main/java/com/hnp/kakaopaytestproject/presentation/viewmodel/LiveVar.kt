package com.hnp.kakaopaytestproject.presentation.viewmodel

import androidx.lifecycle.*
import com.hnp.kakaopaytestproject.presentation.extension.combineWith
import com.hnp.kakaopaytestproject.presentation.nonNullObserve

open class LiveVar<T>(defaultValue: T? = null) {
    protected val _mutableLiveData = MutableLiveData<T>()
    protected val liveData: LiveData<T> = _mutableLiveData
    protected var dataSource: T? = null

    init {
        dataSource = defaultValue
        _mutableLiveData.postValue(dataSource)
    }

    fun set(value: T?) {
        dataSource = value
        _mutableLiveData.postValue(dataSource)
    }

    fun get(): T? = dataSource

    open fun nonNullObserve(lifecycleOwner: LifecycleOwner, observer: (t: T) -> Unit) {
        liveData.nonNullObserve(lifecycleOwner, observer)
    }

    open fun observe(lifecycleOwner: LifecycleOwner, observer: Observer<T?>) {
        liveData.observe(lifecycleOwner, observer)
    }

    fun observe(lifecycleOwner: LifecycleOwner,  observer: (t: T?) -> Unit) {
        liveData.observe(lifecycleOwner, Observer {
            observer(it)
        })
    }

    fun removeOb(observer: Observer<*>) {
        liveData.removeObserver(observer as Observer<in T?>)
    }

    fun <V> map(mapCallback: (T) -> V): LiveData<V> {
        return Transformations.map(liveData) {
            mapCallback(it)
        }
    }

    fun <K, R> combine(anotherLiveVar: LiveVar<K>, block: (T?, K?) -> R): LiveData<R> {
        return liveData.combineWith(anotherLiveVar.liveData, block)
    }

    fun update(updater: (oldVal: T?) -> T?) {
        val nonNullValue = dataSource ?: return
        set(updater(nonNullValue))
    }
}