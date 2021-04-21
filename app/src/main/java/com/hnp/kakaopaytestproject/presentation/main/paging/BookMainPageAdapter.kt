package com.hnp.kakaopaytestproject.presentation.main.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hnp.kakaopaytestproject.R
import com.hnp.kakaopaytestproject.data.remote.book.Document
import kotlinx.android.synthetic.main.viewholder_book_content.view.*

class BookMainPageAdapter(private val action: (content: Document) -> Unit = {}) : PagedListAdapter<Document, BookMainPageAdapter.ViewHolder>(diffCallback) {

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Document>() {
            override fun areItemsTheSame(oldItem: Document, newItem: Document): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Document, newItem: Document): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_book_content, parent, false)
        return ViewHolder(view){
            action.invoke(it)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class ViewHolder(itemView: View, private val clickAction: (content: Document) -> Unit = {}) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Document) {
            itemView.testTextView.text = item.title
            itemView.setOnClickListener { clickAction.invoke(item) }
        }
    }
}