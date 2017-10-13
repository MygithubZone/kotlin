package com.raythinks.poesia.ui.adapter

import android.support.v7.widget.RecyclerView
import android.text.Html
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raythinks.poesia.R
import com.raythinks.poesia.ui.model.ShangxisItem
import com.raythinks.poesia.ui.model.ZiliaosItem
import com.raythinks.poesia.ui.viewmodel.PoesiaDetialViewModel
import kotlinx.android.synthetic.main.item_poesia_fanshang.view.*

/**
 * 功能：<br>
 * 作者：zh<br>
 * 时间： 2017/9/21 0021<br>.
 * 版本：1.2.0
 */
class PoesiaShangAdapter(var viewHodler: PoesiaDetialViewModel) : RecyclerView.Adapter<PoesiaShangAdapter.ViewHolder>() {
    var data: ArrayList<ShangxisItem>

    init {
        data = ArrayList()
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder!!.itemView.tv_content.text = Html.fromHtml(data[position].cont)
        holder!!.itemView.tv_title.text = data[position].nameStr
        var cankao = data[position].cankao
        if (!TextUtils.isEmpty(cankao)) {
            var cankaoArray = cankao.split("&")
            var index = 1;
            var cankaoFinalStr = "<B>参考</B><br/>"
            for (str in cankaoArray) {
                cankaoFinalStr = "${index}.${str}<br/>"
                index++
            }
            holder!!.itemView.tv_cankao.text = Html.fromHtml(cankaoFinalStr)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PoesiaShangAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.item_poesia_fanshang, parent, false))
    }

    override fun getItemCount(): Int = data.size ?: 0

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    }

    fun updateData(newData: ArrayList<ShangxisItem>?) {
        if (newData == null)
            return
        data.clear()
        data.addAll(0, newData)
        notifyItemInserted(0)
    }
}