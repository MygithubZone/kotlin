package com.raythinks.poesia.ui.adapter

import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raythinks.poesia.R
import com.raythinks.poesia.listener.OnItemClickListener
import com.raythinks.poesia.ui.model.AuthorsItem
import com.raythinks.poesia.ui.viewmodel.AuthorListViewModel
import com.raythinks.poesia.utils.ImageUtils
import kotlinx.android.synthetic.main.item_author.view.*

/**
 * 功能：<br>
 * 作者：赵海<br>
 * 时间： 2017/9/21 0021<br>.
 * 版本：1.2.0
 */
class AuthorAdapter(var viewHodler: AuthorListViewModel, var onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<AuthorAdapter.ViewHolder>() {
    var data: ArrayList<AuthorsItem>
    var mOnItemClickListener: OnItemClickListener

    init {
        data = ArrayList()
        mOnItemClickListener = onItemClickListener
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        ImageUtils.loadPoesiaPic(holder!!.itemView.context, data!![position].pic, holder!!.itemView.civ_author_header)
        holder!!.itemView.tv_author_brief.text = Html.fromHtml(data!![position].cont)
        holder!!.itemView.setOnClickListener { mOnItemClickListener.onItemClick(position, holder.itemView) }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): AuthorAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.item_author, parent, false))
    }

    override fun getItemCount(): Int = data.size

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    }

    fun clearData() {
        data.clear()
        notifyDataSetChanged()
    }

    fun updateData(b: Boolean, newData: ArrayList<AuthorsItem>) {
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
                notifyItemRangeInserted(oldSize, data.size-oldSize)
            }
        }
    }
}