package com.hnp.kakaopaytestproject.presentation.viewmodel

import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import kotlin.reflect.KClass

class ViewModelRetrieverImpl<VM : ViewModel>(
    context: Context,
    viewModelClass: KClass<VM>
) : ViewModelRetriever<VM> {
    override val activity: FragmentActivity by lazy {
        try {
            context as FragmentActivity
        } catch (exception: ClassCastException) {
            throw ClassCastException("Please ensure that the provided Context is a valid FragmentActivity")
        }
    }

    override val viewModel: VM by lazy {
        ViewModelLazy(
            viewModelClass,
            { activity.viewModelStore },
            { activity.defaultViewModelProviderFactory }
        ).value
    }
}