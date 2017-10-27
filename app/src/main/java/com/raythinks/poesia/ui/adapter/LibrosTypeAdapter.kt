package com.raythinks.poesia.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raythinks.poesia.R
import com.raythinks.poesia.listener.OnItemClickListener
import com.raythinks.poesia.listener.OnSelectionItemClickListener
import com.raythinks.poesia.ui.model.BookviewsItem
import com.raythinks.poesia.ui.viewmodel.LibrosDetailViewModel
import com.truizlop.sectionedrecyclerview.SimpleSectionedAdapter
import kotlinx.android.synthetic.main.item_libors_mulu.view.*
import java.util.ArrayList

/**
 * 功能：<br>
 * 作者：zh<br>
 * 时间： 2017/9/21 0021<br>.
 * 版本：1.2.0
 */
class LibrosTypeAdapter(context: Context, onItemClickListener: OnSelectionItemClickListener) : SimpleSectionedAdapter<LibrosTypeAdapter.ViewHolder>() {
    var headerAarry = ArrayList<Array<String>>()
    var headerIndexs: Array<String>
    var mItemClickListener: OnSelectionItemClickListener

    init {
        mItemClickListener = onItemClickListener
        headerIndexs = context.resources.getStringArray(R.array.arrayt_libros_type)
        headerAarry.add(context.resources.getStringArray(R.array.arrayt_libros_type_jb))
        headerAarry.add(context.resources.getStringArray(R.array.arrayt_libros_type_sb))
        headerAarry.add(context.resources.getStringArray(R.array.arrayt_libros_type_zb))
        headerAarry.add(context.resources.getStringArray(R.array.arrayt_libros_type_jb1))
    }

    override fun getSectionHeaderTitle(section: Int): String {
        return headerIndexs.get(section)
    }

    override fun onBindItemViewHolder(holder: ViewHolder?, section: Int, position: Int) {
        holder!!.itemView.tv_libros_mulu.text = headerAarry[section]!![position]
        holder!!.itemView.tv_libros_mulu.setOnClickListener { mItemClickListener.onItemClick(section, position, it) }
    }

    override fun onCreateItemViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.item_libors_mulu, parent, false))
    }

    override fun getLayoutResource() = R.layout.header_textview
    override fun getTitleTextID() = R.id.tv_header_item
    override fun getSectionCount() = headerAarry.size
    override fun getItemCountForSection(section: Int): Int {
        return headerAarry[section]!!.size
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    }
}