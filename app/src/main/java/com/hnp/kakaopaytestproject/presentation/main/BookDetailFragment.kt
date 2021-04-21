package com.hnp.kakaopaytestproject.presentation.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.hnp.kakaopaytestproject.KakaoApp
import com.hnp.kakaopaytestproject.R
import com.hnp.kakaopaytestproject.presentation.main.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_main.*

class BookDetailFragment : Fragment(R.layout.fragment_main) {
    private val viewModel by activityViewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.e("hnp", "MainFragment onViewCreated")

        testTextView.text = "테스트 입력"
        if(savedInstanceState == null){
        }

        viewModel.requestSearchBook("미움받을 용기")
    }

    override fun onAttach(context: Context) {
        (requireActivity().application as KakaoApp)
            .appComponent
            .bookDetailComponent()
            .create()
            .inject(this)

        super.onAttach(context)
    }

    companion object {
        fun newInstance(): BookDetailFragment {
            return BookDetailFragment()
        }

        private var schemeBundle: Bundle? = null
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
    }
}