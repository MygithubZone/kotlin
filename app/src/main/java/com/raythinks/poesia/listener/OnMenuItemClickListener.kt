package com.raythinks.poesia.listener

import android.view.View

/**
 * 功能：<br>
 * 作者：赵海<br>
 * 时间： 2017/9/26 0026<br>.
 * 版本：1.2.0
 */
interface OnMenuItemClickListener {
    fun onItemClick(selection: Int,position: Int, itemView: View)
}