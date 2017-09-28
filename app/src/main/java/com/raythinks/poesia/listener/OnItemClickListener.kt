package com.raythinks.poesia.listener

import android.support.v7.widget.RecyclerView
import android.view.View
import com.raythinks.poesia.ui.adapter.LibrosAdapter

var LibrosAdapter.onItemListener: OnItemClickListener
    get() {
        return this.onItemListener
    }
    set(value) {
        this.onItemListener = value
    }
/**
 * 功能：<br>
 * 作者：赵海<br>
 * 时间： 2017/9/26 0026<br>.
 * 版本：1.2.0
 */
interface OnItemClickListener {
    fun onItemClick(position: Int, view: View, vh: RecyclerView.ViewHolder)
}