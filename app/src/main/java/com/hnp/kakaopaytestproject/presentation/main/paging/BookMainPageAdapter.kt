package com.hnp.kakaopaytestproject.presentation.main.paging

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.hnp.kakaopaytestproject.R
import com.hnp.kakaopaytestproject.data.remote.book.Document
import com.hnp.kakaopaytestproject.presentation.extension.getConvertDateToString
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

            item.layoutPosition = layoutPosition

            Glide.with(itemView.bookImageVIew)
                    .load(item.thumbnail)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .centerCrop()
                    .into(itemView.bookImageVIew)
            itemView.bookNameTextView.text = "책 이름 : \n${item.title}"
            itemView.createDateTextView.text = "출시일 : \n${item.datetime.getConvertDateToString("yyyy년-MM월-dd일")}"
            itemView.singleLineDurationTextView.text = "${item.contents}"
            itemView.priceTextView.text = "${item.price}"
            item.sale_price?.let {
                itemView.priceTextView.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                itemView.priceSaleTextView.text = " / $it"
            }

            itemView.likeCheckBox.setOnCheckedChangeListener(null)
            itemView.likeCheckBox.isChecked = item.isLike

            itemView.likeCheckBox.setOnCheckedChangeListener { _, isChecked ->
                item.isLike = isChecked
            }

            itemView.setOnClickListener { clickAction.invoke(item) }
        }
    }
}