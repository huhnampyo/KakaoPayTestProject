package com.hnp.kakaopaytestproject.presentation.main

import android.content.Context
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.hnp.kakaopaytestproject.KakaoApp
import com.hnp.kakaopaytestproject.R
import com.hnp.kakaopaytestproject.presentation.GlideApp
import com.hnp.kakaopaytestproject.presentation.extension.getConvertDateToString
import com.hnp.kakaopaytestproject.presentation.main.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_book_detail.*

class BookDetailFragment : Fragment(R.layout.fragment_book_detail) {
    private val viewModel by activityViewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI(){

        val selectedBook = viewModel.selectedBook

        selectedBook.get()?.let {

            GlideApp.with(bookDetailImageView)
                    .load(it.thumbnail)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .centerCrop()
                    .into(bookDetailImageView)

            bookNameTextView.text = "책 이름 : ${it.title}"
            bookCreateTextView.text = "출시일 : \n${it.datetime.getConvertDateToString("yyyy년-MM월-dd일")}"
            durationTextView.text = "${it.contents}"
            priceTextView.text = "${it.price}"
            it.sale_price?.let {salePrice ->
                priceTextView.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                priceSaleTextView.text = " / $salePrice"
            }
        }


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
    }
}