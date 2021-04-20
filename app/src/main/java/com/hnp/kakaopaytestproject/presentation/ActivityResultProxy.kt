package com.kbstar.land.presentation

import android.content.Intent
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ActivityResultProxy @Inject constructor() {
    private var callback: ((requestCode: Int, resultCode: Int, data: Intent?) -> Unit)? = null

    fun setListener(callback: (requestCode: Int, resultCode: Int, data: Intent?) -> Unit) {
        this.callback = callback
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callback?.invoke(requestCode, resultCode, data)
    }
}