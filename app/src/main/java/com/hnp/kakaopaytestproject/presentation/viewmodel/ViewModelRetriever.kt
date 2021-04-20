package com.hnp.kakaopaytestproject.presentation.viewmodel

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel

interface ViewModelRetriever<VM : ViewModel> {
    val activity: FragmentActivity

    val viewModel: VM
}