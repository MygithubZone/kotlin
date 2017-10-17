package com.raythinks.poesia.ui.adapter

import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raythinks.poesia.R
import com.raythinks.poesia.ui.model.BookviewsItem
import com.raythinks.poesia.ui.viewmodel.LibrosDetailViewModel
import com.truizlop.sectionedrecyclerview.SimpleSectionedAdapter
import kotlinx.android.synthetic.main.item_libors_mulu.view.*
import java.util.*

/**
 * 功能：<br>
 * 作者：赵海<br>
 * 时间： 2017/9/21 0021<br>.
 * 版本：1.2.0
 */
class LibrosMoreAdapter(var viewHodler: LibrosDetailViewModel) : SimpleSectionedAdapter<LibrosMoreAdapter.ViewHolder>() {
    override fun getSectionHeaderTitle(section: Int): String {
        return headerIndexs.get(section)
    }

    override fun onBindItemViewHolder(holder: ViewHolder?, section: Int, position: Int) {
        holder!!.itemView.tv_libros_mulu.text = headerAarry[section]!![position].nameStr
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

    var headerAarry = ArrayList<ArrayList<BookviewsItem>>()
    var headerIndexs = ArrayList<String>()
    fun updateData(newData: ArrayList<BookviewsItem>?) {
        if (newData == null){
            Log.e("aaaaaa", "000000")
            return
        }
        headerAarry.clear()
        headerIndexs.clear()
        var index = 0
        var selectionStrs = ArrayList<BookviewsItem>()
        for (i in newData.indices) {
            var fenlei: String = newData[i].fenlei
            if (TextUtils.isEmpty(fenlei)) {
                fenlei = "篇章"
            }
            if (!headerIndexs.contains(fenlei)) {
                headerIndexs.add(fenlei)
                selectionStrs = ArrayList<BookviewsItem>()
                selectionStrs.add(newData[i])
                headerAarry.add(selectionStrs);
            } else {
                selectionStrs!!.add(newData[i])
            }
        }
        for (i in headerAarry.indices) {
            Log.e("aaaaaa", "${i}.....${headerAarry[i].size}")
        }
        notifyItemInserted(0)
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    }
}


