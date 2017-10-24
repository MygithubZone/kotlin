package com.raythinks.poesia.ui.adapter

import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raythinks.poesia.R
import com.raythinks.poesia.listener.OnSelectionItemClickListener
import com.raythinks.poesia.ui.model.BookviewsItem
import com.raythinks.poesia.ui.model.SearchModel
import com.raythinks.poesia.utils.TUtils
import com.raythinks.shiwen.viewmodel.MainViewModel
import com.truizlop.sectionedrecyclerview.SimpleSectionedAdapter
import kotlinx.android.synthetic.main.item_libors_mulu.view.*
import java.util.ArrayList

/**
 * 功能：<br>
 * 作者：赵海<br>
 * 时间： 2017/9/21 0021<br>.
 * 版本：1.2.0
 */
class SearchAdapter(var viewHodler: MainViewModel, var lis: OnSelectionItemClickListener) : SimpleSectionedAdapter<SearchAdapter.ViewHolder>() {
    var onSelectLis: OnSelectionItemClickListener

    init {
        onSelectLis = lis
    }

    override fun getSectionHeaderTitle(section: Int): String {
        return headerIndexs.get(section)
    }

    override fun onBindItemViewHolder(holder: ViewHolder?, section: Int, position: Int) {
        holder!!.itemView.tv_libros_mulu.gravity=Gravity.LEFT.and(Gravity.CENTER_VERTICAL)
        holder!!.itemView.tv_libros_mulu.text = headerAarry[section]!![position]
        holder!!.itemView.tv_libros_mulu.setOnClickListener { onSelectLis.onItemClick(section, position, holder?.itemView) }
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

    var headerAarry = ArrayList<ArrayList<String>>()
    var headerIndexs = ArrayList<String>()
    var data: SearchModel? = null;
    fun updateData(newData: SearchModel) {
        this.data = newData
        headerAarry.clear()
        headerIndexs.clear()
        addData(newData)
    }

    private fun addData(newData: SearchModel) {
        if (!TUtils.isEmptyList(newData.gushiwens)) {
            headerIndexs.add("诗文")
            var list = ArrayList<String>()
            newData.gushiwens.forEach {
                list.add(it.nameStr)
            }
            headerAarry.add(list)
        }

        if (!TUtils.isEmptyList(newData.mingjus)) {
            headerIndexs.add("名句")
            var list = ArrayList<String>()
            newData.mingjus.forEach {
                list.add(it.nameStr)
            }
            headerAarry.add(list)
        }

        if (!TUtils.isEmptyList(newData.typekeys)) {
            headerIndexs.add("类型")
            var list = ArrayList<String>()
            newData.typekeys.forEach {
                list.add(it.nameStr)
            }
            headerAarry.add(list)
        }
        if (!TUtils.isEmptyList(newData.authors)) {
            headerIndexs.add("作者")
            var list = ArrayList<String>()
            newData.authors.forEach {
                list.add(it.nameStr)
            }
            headerAarry.add(list)
        }
        if (!TUtils.isEmptyList(newData.books)) {
            headerIndexs.add("古籍")
            var list = ArrayList<String>()
            newData.books.forEach {
                list.add(it.nameStr)
            }
            headerAarry.add(list)
        }
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    }
}