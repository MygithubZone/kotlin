package com.raythinks.poesia.ui.adapter

import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raythinks.poesia.R
import com.raythinks.poesia.ui.model.BooksItem
import com.raythinks.poesia.ui.model.GushiwensItem
import com.raythinks.poesia.ui.model.ZiliaosItem
import com.raythinks.poesia.ui.viewmodel.AuthorDetialViewModel
import com.raythinks.poesia.ui.viewmodel.LibrosViewModel
import kotlinx.android.synthetic.main.item_author_ziliao.view.*
import kotlinx.android.synthetic.main.item_libros.view.*

/**
 * 功能：<br>
 * 作者：赵海<br>
 * 时间： 2017/9/21 0021<br>.
 * 版本：1.2.0
 */
class AuthorMoreAdapter(var viewHodler: AuthorDetialViewModel) : RecyclerView.Adapter<AuthorMoreAdapter.ViewHolder>() {
    var data: ArrayList<ZiliaosItem>

    init {
        data = ArrayList()
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder!!.itemView.tv_author_content.text = Html.fromHtml(data[position].cont)
        holder!!.itemView.tv_author_title.text = data[position].nameStr
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): AuthorMoreAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.item_author_ziliao, parent, false))
    }

    override fun getItemCount(): Int = data.size ?: 0

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    }

    fun updateData(newData: ArrayList<ZiliaosItem>?) {
        if (newData == null)
            return
        data.clear()
        data.addAll(0, newData)
        notifyItemInserted(0)
    }
}