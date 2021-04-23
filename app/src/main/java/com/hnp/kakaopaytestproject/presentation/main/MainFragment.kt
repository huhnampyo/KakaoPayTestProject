package com.hnp.kakaopaytestproject.presentation.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.hnp.kakaopaytestproject.KakaoApp
import com.hnp.kakaopaytestproject.R
import com.hnp.kakaopaytestproject.presentation.main.paging.BookMainPageAdapter
import com.hnp.kakaopaytestproject.presentation.main.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewModel by activityViewModels<MainViewModel>()

    private val bookMainPageAdapter by lazy { createBookMainPageAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.e("hnp", "MainFragment onViewCreated")

        initUI()
        initObservable()
        initSearchViewListener()
    }

    /**
     * paging 어뎁터를 설정.
     */
    private fun initUI(){
        contentRecyclerView.apply {
            addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
            setHasFixedSize(true)
            adapter = bookMainPageAdapter
        }
    }

    /**
     * 선택된 책의 변경이 이루어질때 화면 갱신 (옵저버)
     */
    private fun initObservable(){
        viewModel.errorMsg.nonNullObserve(this){
            if(it.isNotEmpty()){
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
            }
        }

        viewModel.isLikeChanged.nonNullObserve(this) {
            bookMainPageAdapter.notifyDataSetChanged()
        }
    }

    /**
     * 상단 검색뷰의 이벤트를 리스닝
     */
    private fun initSearchViewListener() {
        searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.requestSearchBook(it).observe(
                            viewLifecycleOwner, Observer { items ->
                        items?.let { bookMainPageAdapter.submitList(items) }
                    })
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    /**
     * pageAdapter를 생성합니다.
     */
    private fun createBookMainPageAdapter(): BookMainPageAdapter {
        return BookMainPageAdapter {
            viewModel.selectedBook.set(it)
        }
    }

    /**
     * onAttach 시 의존성을 주입해줍니다.
     */
    override fun onAttach(context: Context) {
        (requireActivity().application as KakaoApp)
                .appComponent
                .mainComponent()
                .create()
                .inject(this)

        super.onAttach(context)
    }
}