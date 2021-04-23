package com.hnp.kakaopaytestproject.presentation.main

import android.content.Context
import android.graphics.Paint
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.hnp.kakaopaytestproject.KakaoApp
import com.hnp.kakaopaytestproject.R
import com.hnp.kakaopaytestproject.presentation.extension.getConvertDateToString
import com.hnp.kakaopaytestproject.presentation.main.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_book_detail.*


class BookDetailFragment : Fragment(R.layout.fragment_book_detail) {
    private val viewModel by activityViewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        initUI()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_list,menu)

        val menuItem = menu.findItem(R.id.tab1)
        menuItem.isChecked = viewModel.selectedBook.get()?.isLike!!
        checkLikeMenuItem(menuItem)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.tab1 ->{
                viewModel.isLikeChanged.set(true)

                item.isChecked = !item.isChecked
                viewModel.selectedBook.get()?.isLike = item.isChecked
                checkLikeMenuItem(item)
                return true
            }
            android.R.id.home ->{
                requireActivity().onBackPressed()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun checkLikeMenuItem(menuItem : MenuItem){
        menuItem.icon = ContextCompat.getDrawable(requireContext(),when(viewModel.selectedBook.get()?.isLike!!){
            true -> R.drawable.like_select
            else -> R.drawable.like_normal
        })
    }

    private fun initUI(){

        val selectedBook = viewModel.selectedBook

        selectedBook.get()?.let {

            Glide.with(bookDetailImageView)
                .load(it.thumbnail)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .centerCrop()
                .into(bookDetailImageView)

            bookNameTextView.text = "책 이름 : ${it.title}"
            bookCreateTextView.text = "출시일 : ${it.datetime.getConvertDateToString("yyyy년-MM월-dd일")}"
            publisherTextView.text = "출판사 : ${it.publisher}"
            durationTextView.text = if(it.contents.isNotEmpty()) "${it.contents}" else "책 내용이 없습니다."
            durationTextView.movementMethod = ScrollingMovementMethod()

            priceTextView.text = "${it.price}"
            it.sale_price?.let {salePrice ->
                if(salePrice > 0) {
                    priceTextView.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                    priceSaleTextView.text = " / $salePrice"
                }
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
}