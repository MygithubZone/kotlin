package com.raythinks.poesia.ui.adapter

import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raythinks.poesia.R
import com.raythinks.poesia.ui.model.BooksItem
import com.raythinks.poesia.ui.model.GushiwensItem
import com.raythinks.poesia.ui.viewmodel.LibrosViewModel
import kotlinx.android.synthetic.main.item_libros.view.*

/**
 * 功能：<br>
 * 作者：赵海<br>
 * 时间： 2017/9/21 0021<br>.
 * 版本：1.2.0
 */
class LibrosAdapter(var viewHodler: LibrosViewModel) : RecyclerView.Adapter<LibrosAdapter.ViewHolder>() {
    var data: ArrayList<BooksItem>

    init {
        data = ArrayList()
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder!!.itemView.tv_libros_title.text = data[position].nameStr
        holder!!.itemView.tv_libros_brief.text = Html.fromHtml(data[position].cont)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): LibrosAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.item_libros, parent, false))
    }

    override fun getItemCount(): Int = data.size ?: 0

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    }

    fun updateData(b: Boolean, newData: ArrayList<BooksItem>) {
        if (b) data.clear()
        val addPosition = data.size
        data.addAll(addPosition, newData)
        if (addPosition != 0)
            notifyItemInserted(addPosition)
        else
            notifyDataSetChanged()
    }
}