package com.raythinks.poesia.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raythinks.poesia.R
import com.raythinks.poesia.ui.viewmodel.RefranesViewModel

/**
 * 功能：<br>
 * 作者：赵海<br>
 * 时间： 2017/9/21 0021<br>.
 * 版本：1.2.0
 */
class RefranesAdapter(var viewHodler: RefranesViewModel) : RecyclerView.Adapter<RefranesAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RefranesAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.item_refranes, parent, false))
    }

    override fun getItemCount(): Int = 8

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    }
}