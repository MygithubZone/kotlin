package com.raythinks.poesia.ui.adapter

import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raythinks.poesia.R
import com.raythinks.poesia.listener.CopyActionCallBack
import com.raythinks.poesia.listener.OnItemClickListener
import com.raythinks.poesia.ui.model.GushiwensItem
import com.raythinks.poesia.ui.viewmodel.BasePoesiaViewModel
import kotlinx.android.synthetic.main.item_poesia.view.*

/**
 * 功能：<br>
 * 作者：赵海<br>
 * 时间： 2017/9/21 0021<br>.
 * 版本：1.2.0
 */
class PoesiaAdapter(var viewHodler: BasePoesiaViewModel, var onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<PoesiaAdapter.ViewHolder>() {
    var data: ArrayList<GushiwensItem>

    init {
        data = ArrayList()
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder!!.itemView.tv_poesia_title.text = data[position].nameStr
        holder!!.itemView.tv_poesia_author.text = "${data[position].chaodai}.${data[position].author}"
        holder!!.itemView.tv_poesia_content.text = Html.fromHtml(data[position].cont) as Editable
        holder!!.itemView.tv_poesia_tag.text = data[position].tag
        holder!!.itemView.tv_poesia_content.clearFocus()
        holder!!.itemView.tv_poesia_content.setCustomActionMenuCallBack(CopyActionCallBack())
        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(position, holder.itemView)
        }
        holder!!.itemView.tv_poesia_content.setOnClickListener {
            onItemClickListener.onItemClick(position, holder.itemView)
        }
        if (data[position].yizhuIspass) {
            holder!!.itemView.ll_yizhu.visibility = View.VISIBLE
        } else {
            holder!!.itemView.ll_yizhu.visibility = View.GONE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PoesiaAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.item_poesia, parent, false))
    }

    override fun getItemCount(): Int = data.size ?: 0

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    }

    fun updateData(b: Boolean, newData: ArrayList<GushiwensItem>) {
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

    fun clearData() {
        data.clear()
        notifyDataSetChanged()
    }
}