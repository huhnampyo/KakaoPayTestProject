package com.hnp.kakaopaytestproject.presentation.extension

import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hnp.kakaopaytestproject.presentation.extension.addObserver

fun RecyclerView.addOnScrollListener(
    lifecycleOwner: LifecycleOwner,
    listener: RecyclerView.OnScrollListener
) {
    lifecycleOwner.addObserver(
        onStartCallback = {
            addOnScrollListener(listener)
        },
        onDestroyCallback = {
            removeOnScrollListener(listener)
        }
    )
}

fun RecyclerView.addOnPageChangedListener(listener: (pos: Int) -> Unit) {
    val layoutManager = this.layoutManager as LinearLayoutManager
    var lastPos = -1

    this.addOnScrollListener(object: RecyclerView.OnScrollListener() {
        // RecyclerView's onScrollStateChanged() is unstable and doesn't work as expected at least in 27.0.2.
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            val pos = layoutManager.findFirstCompletelyVisibleItemPosition()
            if (pos != -1 && pos != lastPos) {
                lastPos = pos
                listener.invoke(pos)
            }
        }
    })
}