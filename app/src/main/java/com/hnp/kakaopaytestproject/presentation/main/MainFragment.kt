package com.hnp.kakaopaytestproject.presentation.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
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

    private fun initUI(){
        contentRecyclerView.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
        contentRecyclerView.setHasFixedSize(true)
        contentRecyclerView.adapter = bookMainPageAdapter
    }

    private fun createBookMainPageAdapter(): BookMainPageAdapter {
        return BookMainPageAdapter {
//            viewModel.selectedBook.set(it)
        }
    }

    override fun onAttach(context: Context) {
        (requireActivity().application as KakaoApp)
            .appComponent
            .mainComponent()
            .create()
            .inject(this)

        super.onAttach(context)
    }

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment()
        }

        private var schemeBundle: Bundle? = null
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
    }
}