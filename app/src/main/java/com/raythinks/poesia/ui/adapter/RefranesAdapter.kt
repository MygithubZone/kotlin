package com.raythinks.poesia.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raythinks.poesia.R
import com.raythinks.poesia.listener.OnItemClickListener
import com.raythinks.poesia.ui.model.MingjusItem
import com.raythinks.shiwen.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.item_poesia.view.*
import kotlinx.android.synthetic.main.item_refranes.view.*

/**
 * 功能：<br>
 * 作者：赵海<br>
 * 时间： 2017/9/21 0021<br>.
 * 版本：1.2.0
 */
class RefranesAdapter(var viewHodler: MainViewModel, var onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<RefranesAdapter.ViewHolder>() {
    var data: ArrayList<MingjusItem>

    init {
        data = ArrayList()
    }

    fun clearData() {
        data.clear()
        notifyDataSetChanged()
    }

    fun updateData(b: Boolean, newData: ArrayList<MingjusItem>) {
        if (data.size == 0) {
            data.addAll(newData)
            notifyItemRangeInserted(0, data.size)
        } else {
            if (b) {
                data.clear()
                data.addAll(newData)
                notifyDataSetChanged()
            } else {
                var oldSize = data.size
                data.addAll(oldSize, newData)
                notifyItemRangeInserted(oldSize, data.size - oldSize)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.itemView?.tv_refranes_content?.text = data[position].nameStr
        holder?.itemView?.tv_refranes_from?.text = "${data[position].shiName}(${data[position].author})"
        holder?.itemView?.setOnClickListener {
            onItemClickListener.onItemClick(position, holder.itemView)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RefranesAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.item_refranes, parent, false))
    }

    override fun getItemCount(): Int = data.size ?: 0

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    }
}