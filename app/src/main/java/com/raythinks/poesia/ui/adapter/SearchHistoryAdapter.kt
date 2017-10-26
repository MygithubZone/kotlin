package com.raythinks.poesia.ui.adapter

import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raythinks.poesia.R
import com.raythinks.poesia.listener.OnItemClickListener
import com.raythinks.poesia.utils.ImageUtils
import com.raythinks.shiwen.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.item_search_reult.view.*

/**
 * 功能：历史搜索<br>
 * 作者：zh<br>
 * 时间： 2017/9/21 0021<br>.
 * 版本：1.2.0
 */
class SearchHistoryAdapter(var viewHodler: MainViewModel, var onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<SearchHistoryAdapter.ViewHolder>() {
    var data: ArrayList<String>
    var mOnItemClickListener: OnItemClickListener

    init {
        data = ArrayList()
        mOnItemClickListener = onItemClickListener
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder!!.itemView.tv_search_result.text = data!![position]
        holder!!.itemView.setOnClickListener { mOnItemClickListener.onItemClick(position, holder.itemView) }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SearchHistoryAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.item_search_reult, parent, false))
    }

    override fun getItemCount(): Int = data.size

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    }

    fun clearData() {
        data.clear()
        notifyDataSetChanged()
    }

    fun updateData(newData: ArrayList<String>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }
}