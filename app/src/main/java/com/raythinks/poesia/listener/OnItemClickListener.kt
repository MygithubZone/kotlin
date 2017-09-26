package com.raythinks.poesia.listener

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * 功能：<br>
 * 作者：赵海<br>
 * 时间： 2017/9/26 0026<br>.
 * 版本：1.2.0
 */
interface OnItemClickListener {
    fun onItemClick(position: Int, view: View, vh: RecyclerView.ViewHolder)
}