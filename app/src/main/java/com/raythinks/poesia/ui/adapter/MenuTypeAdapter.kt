package com.raythinks.poesia.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raythinks.poesia.R
import com.raythinks.poesia.listener.OnSelectionItemClickListener
import com.truizlop.sectionedrecyclerview.SimpleSectionedAdapter
import kotlinx.android.synthetic.main.item_libors_mulu.view.*
import java.util.*

/**
 * 功能：<br>
 * 作者：zh<br>
 * 时间： 2017/9/21 0021<br>.
 * 版本：1.2.0
 */
class MenuTypeAdapter(theme: String, selectType: Int, array: Array<String>,onItemClickListener: OnMenuItemClickListener) : SimpleSectionedAdapter<MenuTypeAdapter.ViewHolder>() {
    var itemArray = ArrayList<Array<String>>()
    var mItemClickListener: OnMenuItemClickListener
    var theme = "";
    var selectType: Int = 1
    var selectSum = 0

    interface OnMenuItemClickListener {
        fun onItemClick(ad: MenuTypeAdapter, selection: Int, position: Int, itemView: View)
    }

    init {
        mItemClickListener = onItemClickListener
        itemArray.clear()
        itemArray.add(array)
        this.theme = theme
        this.selectType = selectType
        this.selectSum = 1
    }


    override fun getSectionHeaderTitle(section: Int): String {
        return theme
    }

    override fun onBindItemViewHolder(holder: ViewHolder?, section: Int, position: Int) {
        holder!!.itemView.tv_libros_mulu.text = itemArray[section]!![position]
        holder!!.itemView.tv_libros_mulu.setOnClickListener { mItemClickListener.onItemClick(this@MenuTypeAdapter, section, position, it) }
    }

    override fun onCreateItemViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.item_libors_mulu, parent, false))
    }

    override fun getLayoutResource() = R.layout.header_textview
    override fun getTitleTextID() = R.id.tv_header_item
    override fun getSectionCount() = selectSum
    override fun getItemCountForSection(section: Int): Int {
        return itemArray[section]!!.size
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    }
}