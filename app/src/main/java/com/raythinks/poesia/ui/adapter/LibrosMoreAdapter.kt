package com.raythinks.poesia.ui.adapter

import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.raythinks.poesia.R
import com.raythinks.poesia.ui.model.BookviewsItem
import com.raythinks.poesia.ui.viewmodel.LibrosDetailViewModel
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter
import kotlinx.android.synthetic.main.header_textview.view.*
import kotlinx.android.synthetic.main.item_libors_mulu.view.*

/**
 * 功能：<br>
 * 作者：赵海<br>
 * 时间： 2017/9/21 0021<br>.
 * 版本：1.2.0
 */
class LibrosMoreAdapter(var viewHodler: LibrosDetailViewModel) : RecyclerView.Adapter<LibrosMoreAdapter.ViewHolder>(), StickyRecyclerHeadersAdapter<LibrosMoreAdapter.ViewHolder> {
    override fun onCreateHeaderViewHolder(parent: ViewGroup?): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.header_textview, parent, false))
    }

    override fun getHeaderId(position: Int): Long {
        if (TextUtils.isEmpty(data[position].fenlei)) {
            return -1
        } else {
            return data[position].fenlei[0].toLong()
        }
        return -1
    }

    override fun onBindHeaderViewHolder(holder: ViewHolder?, position: Int) {
        holder!!.itemView.tv_header_item.text = data[position].fenlei
    }


    var data: ArrayList<BookviewsItem>

    init {
        data = ArrayList()
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder!!.itemView.tv_libros_mulu.text = data[position].nameStr
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder? {
        return ViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.item_libors_mulu, parent, false))
    }

    override fun getItemCount(): Int = data.size ?: 0

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    }

    fun updateData(newData: ArrayList<BookviewsItem>?) {
        if (newData == null)
            return
        data.addAll(newData)
        notifyItemInserted(0)
    }
}