package com.raythinks.poesia.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raythinks.poesia.R
import com.raythinks.poesia.ui.model.MingjusItem
import com.raythinks.poesia.ui.viewmodel.RefranesViewModel
import kotlinx.android.synthetic.main.item_refranes.view.*

/**
 * 功能：<br>
 * 作者：赵海<br>
 * 时间： 2017/9/21 0021<br>.
 * 版本：1.2.0
 */
class RefranesAdapter(var viewHodler: RefranesViewModel) : RecyclerView.Adapter<RefranesAdapter.ViewHolder>() {
    var data: ArrayList<MingjusItem>

    init {
        data = ArrayList()
    }

    fun updateData(b: Boolean, newData: ArrayList<MingjusItem>) {
        if (b) data.clear()
        val addPosition = data.size
        data.addAll(addPosition, newData)
        if (addPosition != 0)
            notifyItemInserted(addPosition)
        else
            notifyDataSetChanged()


    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.itemView?.tv_refranes_content?.text = data[position].nameStr
        holder?.itemView?.tv_refranes_from?.text = "${data[position].shiName}(${data[position].author})"

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RefranesAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.item_refranes, parent, false))
    }

    override fun getItemCount(): Int = data.size?: 0

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    }
}